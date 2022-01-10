// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.function.*;
import org.apache.commons.io.*;
import com.machinezoo.noexception.*;
import com.machinezoo.sourceafis.*;

public class TestResources {
	private static byte[] load(String name) {
		return Exceptions.sneak().get(() -> {
			try (var stream = TestResources.class.getResourceAsStream(name)) {
				return IOUtils.toByteArray(stream);
			}
		});
	}
	public static byte[] probe() {
		return load("probe.png");
	}
	public static byte[] candidate() {
		return load("candidate.png");
	}
	public static void log(Supplier<FingerprintTransparency> factory) {
		var candidate = new FingerprintTemplate(
			new FingerprintImage(candidate()));
		try (var transparency = factory.get()) {
			new FingerprintMatcher(new FingerprintTemplate(new FingerprintImage(probe()))).match(candidate);
		}
	}
	public static TransparencyBuffer buffer() {
		var buffer = new TransparencyBuffer();
		log(buffer::capture);
		return buffer;
	}
}
