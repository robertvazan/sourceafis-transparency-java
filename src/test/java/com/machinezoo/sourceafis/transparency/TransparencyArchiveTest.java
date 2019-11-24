// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.zip.*;
import org.apache.commons.io.*;
import org.junit.*;
import com.machinezoo.noexception.*;
import com.machinezoo.sourceafis.*;

public class TransparencyArchiveTest {
	private static byte[] load(String name) {
		return Exceptions.sneak().get(() -> {
			try (InputStream stream = TransparencyArchiveTest.class.getResourceAsStream(name)) {
				return IOUtils.toByteArray(stream);
			}
		});
	}
	@Test public void getters() throws IOException {
		FingerprintTemplate candidate = new FingerprintTemplate(
			new FingerprintImage()
				.decode(load("candidate.png")));
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try (FingerprintTransparency transparency = FingerprintTransparency.zip(buffer)) {
			new FingerprintMatcher()
				.index(new FingerprintTemplate(
					new FingerprintImage()
						.decode(load("probe.png"))))
				.match(candidate);
		}
		Path temporary = Files.createTempFile("", ".zip");
		try {
			Files.write(temporary, buffer.toByteArray());
			try (ZipFile zip = new ZipFile(temporary.toFile())) {
				TransparencyArchive archive = new TransparencyZip(zip);
				int count = 0;
				for (Method method : archive.getClass().getMethods()) {
					if (method.getParameterCount() == 0 && method.getDeclaringClass() != Object.class) {
						++count;
						assertNotNull(method.getName(), Exceptions.sneak().get(() -> method.invoke(archive)));
					}
				}
				assertThat(count, greaterThan(5));
			}
		} finally {
			Files.delete(temporary);
		}
	}
}
