// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static java.util.stream.Collectors.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import org.apache.commons.io.*;
import com.machinezoo.noexception.*;

public class TransparencyZip extends TransparencyArchive {
	private final ZipFile zip;
	public TransparencyZip(ZipFile zip) {
		this.zip = zip;
	}
	@Override public List<String> enumerate() {
		return Collections.list(zip.entries()).stream()
			.map(e -> e.getName())
			.collect(toList());
	}
	@Override public byte[] read(String path) {
		ZipEntry entry = zip.getEntry(path);
		if (entry == null)
			return null;
		return Exceptions.wrap().get(() -> {
			try (InputStream stream = zip.getInputStream(entry)) {
				return IOUtils.toByteArray(stream);
			}
		});
	}
}
