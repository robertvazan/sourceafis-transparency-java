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
	public static TransparencyArchive archive() {
		var buffer = new TransparencyBuffer();
		log(buffer::open);
		return buffer.toArchive();
	}
	public static TransparencyArchive archive(TransparentOperation operation) {
		return switch (operation) {
			case EXTRACT_FEATURES -> new TransparencyBuffer()
				.capture(() -> new FingerprintTemplate(new FingerprintImage(probe())))
				.toArchive();
			case PREPARE_PROBE -> {
				var template = new FingerprintTemplate(new FingerprintImage(probe()));
				yield new TransparencyBuffer()
					.capture(() -> new FingerprintMatcher(template))
					.toArchive();
			}
			case COMPARE_CANDIDATE -> {
				var matcher = new FingerprintMatcher(new FingerprintTemplate(new FingerprintImage(probe())));
				var candidate = new FingerprintTemplate(new FingerprintImage(candidate()));
				yield new TransparencyBuffer()
					.capture(() -> matcher.match(candidate))
					.toArchive();
			}
			case DESERIALIZE_TEMPLATE -> {
				var template = new FingerprintTemplate(new FingerprintImage(probe())).toByteArray();
				yield new TransparencyBuffer()
					.capture(() -> new FingerprintTemplate(template))
					.toArchive();
			}
		};
	}
}
