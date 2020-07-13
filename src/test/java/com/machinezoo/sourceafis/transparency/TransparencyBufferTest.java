// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.function.*;
import org.apache.commons.io.*;
import org.junit.jupiter.api.*;
import com.machinezoo.noexception.*;
import com.machinezoo.sourceafis.*;

public class TransparencyBufferTest {
	private static byte[] load(String name) {
		return Exceptions.sneak().get(() -> {
			try (InputStream stream = TransparencyArchiveTest.class.getResourceAsStream(name)) {
				return IOUtils.toByteArray(stream);
			}
		});
	}
	public static void capture(Supplier<FingerprintTransparency> supplier) {
		FingerprintTemplate candidate = new FingerprintTemplate(
			new FingerprintImage()
				.decode(load("candidate.png")));
		try (FingerprintTransparency transparency = supplier.get()) {
			new FingerprintMatcher()
				.index(new FingerprintTemplate(
					new FingerprintImage()
						.decode(load("probe.png"))))
				.match(candidate);
		}
	}
	public static TransparencyBuffer capture() {
		TransparencyBuffer buffer = new TransparencyBuffer();
		capture(buffer::capture);
		return buffer;
	}
	public static TransparencyBuffer unzip() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		capture(() -> FingerprintTransparency.zip(stream));
		TransparencyBuffer buffer = new TransparencyBuffer();
		buffer.unzip(new ByteArrayInputStream(stream.toByteArray()));
		return buffer;
	}
	@Test
	public void equalCaptureMethods() {
		TransparencyBuffer captured = capture();
		TransparencyBuffer zipped = unzip();
		assertEquals(captured.paths(), zipped.paths());
		for (TransparencyPath path : captured.paths()) {
			assertEquals(captured.count(path), zipped.count(path));
			for (int offset = 0; offset < captured.count(path); ++offset)
				assertArrayEquals(captured.read(path, offset), zipped.read(path, offset));
		}
	}
}
