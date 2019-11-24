// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static java.util.stream.Collectors.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.zip.*;
import org.apache.commons.io.*;
import com.machinezoo.noexception.*;
import com.machinezoo.sourceafis.*;

public class TransparencyBuffer extends TransparencyArchive {
	private final Map<TransparencyPath, List<byte[]>> map = new HashMap<>();
	@Override public List<TransparencyPath> paths() {
		return map.keySet().stream()
			.sorted(Comparator.<TransparencyPath, String>comparing(p -> p.keyword()).thenComparing(p -> p.suffix()))
			.collect(toList());
	}
	@Override public int count(TransparencyPath path) {
		return map.getOrDefault(path, Collections.emptyList()).size();
	}
	@Override public byte[] read(TransparencyPath path, int offset) {
		List<byte[]> records = map.getOrDefault(path, Collections.emptyList());
		return offset < records.size() ? records.get(offset) : null;
	}
	public void clear() {
		map.clear();
	}
	public void add(TransparencyPath path, byte[] data) {
		if (data != null)
			map.computeIfAbsent(path, p -> new ArrayList<>()).add(data);
	}
	private static final Pattern filenameRe = Pattern.compile("^[0-9]+-([a-z-]+)(.[a-z]+)$");
	public void unzip(InputStream stream) {
		Exceptions.wrap().run(() -> {
			try (ZipInputStream zip = new ZipInputStream(stream)) {
				while (true) {
					ZipEntry entry = zip.getNextEntry();
					if (entry == null)
						break;
					Matcher matcher = filenameRe.matcher(entry.getName());
					if (!matcher.matches())
						continue;
					TransparencyPath path = new TransparencyPath(matcher.group(1), matcher.group(2));
					add(path, IOUtils.toByteArray(zip));
				}
			}
		});
	}
	public FingerprintTransparency capture() {
		return new FingerprintTransparency() {
			@Override protected void capture(String keyword, Map<String, Supplier<byte[]>> data) {
				for (String suffix : data.keySet())
					add(new TransparencyPath(keyword, suffix), data.get(suffix).get());
			}
		};
	}
}
