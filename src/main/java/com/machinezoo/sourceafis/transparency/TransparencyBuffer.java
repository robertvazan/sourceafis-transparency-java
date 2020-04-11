// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static java.util.stream.Collectors.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import org.apache.commons.io.*;
import com.machinezoo.noexception.*;
import com.machinezoo.sourceafis.*;

public class TransparencyBuffer extends TransparencyArchive {
	private final Map<TransparencyPath, List<byte[]>> map = new HashMap<>();
	private final Map<TransparencyPath, String> mimes = new HashMap<>();
	@Override public List<TransparencyPath> paths() {
		return map.keySet().stream()
			.sorted(Comparator.comparing(p -> p.key()))
			.collect(toList());
	}
	@Override public int count(TransparencyPath path) {
		return map.getOrDefault(path, Collections.emptyList()).size();
	}
	@Override public String mime(TransparencyPath path) {
		return mimes.getOrDefault(path, "application/octet-stream");
	}
	@Override public byte[] read(TransparencyPath path, int offset) {
		List<byte[]> records = map.getOrDefault(path, Collections.emptyList());
		return offset >= 0 && offset < records.size() ? records.get(offset) : null;
	}
	public void clear() {
		map.clear();
	}
	public void add(TransparencyPath path, String mime, byte[] data) {
		if (data != null) {
			if (mime != null)
				mimes.put(path, mime);
			map.computeIfAbsent(path, p -> new ArrayList<>()).add(data);
		}
	}
	public void add(TransparencyPath path, byte[] data) {
		add(path, null, data);
	}
	public void unzip(InputStream stream) {
		Exceptions.wrap().run(() -> {
			try (ZipInputStream zip = new ZipInputStream(stream)) {
				while (true) {
					ZipEntry entry = zip.getNextEntry();
					if (entry == null)
						break;
					if (TransparencyZipPath.valid(entry.getName())) {
						TransparencyZipPath path = new TransparencyZipPath(entry.getName());
						add(path.parsed(), path.mime(), IOUtils.toByteArray(zip));
					}
				}
			}
		});
	}
	public FingerprintTransparency capture() {
		return new FingerprintTransparency() {
			@Override public void take(String key, String mime, byte[] data) {
				add(new TransparencyPath(key), mime, data);
			}
		};
	}
}
