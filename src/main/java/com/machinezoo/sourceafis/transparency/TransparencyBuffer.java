// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.zip.*;
import org.apache.commons.io.*;
import com.machinezoo.noexception.*;
import com.machinezoo.sourceafis.*;
import com.machinezoo.sourceafis.transparency.keys.*;
import com.machinezoo.stagean.*;

public class TransparencyBuffer {
	private final Map<TransparencyKey<?>, List<TransparencyRecord<?>>> map = new HashMap<>();
	private TransparencyFilter filter = TransparencyFilter.any();
	/*
	 * Intentionally applied only to future additions, leaving existing records unaffected,
	 * so that different filters can be applied to different sources of transparency data.
	 */
	public TransparencyBuffer accept(TransparencyFilter filter) {
		this.filter = filter != null ? filter : TransparencyFilter.any();
		return this;
	}
	public boolean accepts(TransparencyKey<?> key) {
		return filter.accepts(key);
	}
	public TransparencyBuffer add(TransparencyRecord<?> record) {
		Objects.requireNonNull(record);
		if (accepts(record.key()))
			map.computeIfAbsent(record.key(), k -> new ArrayList<>()).add(record);
		return this;
	}
	public TransparencyBuffer write(TransparencyKey<?> key, String mime, byte[] data) {
		return add(new TransparencyRecord<>(key, mime, data));
	}
	public TransparencyBuffer write(TransparencyKey<?> key, byte[] data) {
		return write(key, key.mime(), data);
	}
	public <T> TransparencyBuffer serialize(TransparencyKey<T> key, String mime, T object) {
		return write(key, mime, key.serialize(mime, object));
	}
	public <T> TransparencyBuffer serialize(TransparencyKey<T> key, T object) {
		return serialize(key, key.mime(), object);
	}
	public TransparencyBuffer append(Collection<TransparencyRecord<?>> records) {
		for (var record : records)
			add(record);
		return this;
	}
	public TransparencyBuffer append(TransparencyArchive archive) {
		return append(archive.toList());
	}
	public boolean accepts(String key) {
		return filter.accepts(key);
	}
	public TransparencyBuffer take(String key, String mime, byte[] data) {
		return write(TransparencyKey.parse(key), mime, data);
	}
	public FingerprintTransparency open() {
		return new TransparencyBufferLogger(this);
	}
	public TransparencyBuffer capture(Runnable action) {
		try (var transparency = open()) {
			action.run();
		}
		return this;
	}
	private static final Pattern FILENAME_RE = Pattern.compile("^[0-9]+-([a-z-]+)(\\.[a-z]+)$");
	private static String mime(String suffix) {
		return switch (suffix) {
			case ".cbor" -> "application/cbor";
			case ".txt" -> "text/plain";
			case ".json" -> "application/json";
			case ".xml" -> "application/xml";
			case ".jpeg" -> "image/jpeg";
			case ".png" -> "image/png";
			case ".bmp" -> "image/bmp";
			case ".tiff" -> "image/tiff";
			case ".jp2" -> "image/jp2";
			case ".wsq" -> "image/x-wsq";
			default -> "application/octet-stream";
		};
	}
	@DraftCode("Find better solution for checked exceptions.")
	public TransparencyBuffer unzip(InputStream stream) {
		Exceptions.wrap().run(() -> {
			try (var zip = new ZipInputStream(stream)) {
				while (true) {
					var entry = zip.getNextEntry();
					if (entry == null)
						break;
					var matcher = FILENAME_RE.matcher(entry.getName());
					/*
					 * Silently ignore unrecognized files, which could have been automatically generated by OS or various apps.
					 */
					if (matcher.matches())
						TransparencyBuffer.this.take(matcher.group(1), mime(matcher.group(2)), IOUtils.toByteArray(zip));
				}
			}
		});
		return this;
	}
	public TransparencyArchive toArchive() {
		var immutable = new HashMap<TransparencyKey<?>, List<TransparencyRecord<?>>>();
		for (var key : map.keySet())
			immutable.put(key, Collections.unmodifiableList(new ArrayList<>(map.get(key))));
		return new PlainTransparencyArchive(immutable);
	}
}
