// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.concurrent.*;
import java.util.function.Supplier;
import com.google.common.base.*;
import com.machinezoo.sourceafis.*;

public class TestArchives {
	public static TransparencyArchive extractor() {
		return new TransparencyBuffer()
			.capture(() -> new FingerprintTemplate(new FingerprintImage(TestResources.probe())))
			.toArchive();
	}
	public static TransparencyArchive probe() {
		var template = new FingerprintTemplate(new FingerprintImage(TestResources.probe()));
		return new TransparencyBuffer()
			.capture(() -> new FingerprintMatcher(template))
			.toArchive();
	}
	public static TransparencyArchive comparison() {
		var matcher = new FingerprintMatcher(new FingerprintTemplate(new FingerprintImage(TestResources.probe())));
		var candidate = new FingerprintTemplate(new FingerprintImage(TestResources.candidate()));
		return new TransparencyBuffer()
			.capture(() -> matcher.match(candidate))
			.toArchive();
	}
	public static TransparencyArchive deserialization() {
		var serialized = new FingerprintTemplate(new FingerprintImage(TestResources.probe())).toByteArray();
		return new TransparencyBuffer()
			.capture(() -> new FingerprintTemplate(serialized))
			.toArchive();
	}
	private static final ConcurrentMap<TransparentOperation, TransparencyArchive> archives = new ConcurrentHashMap<>();
	public static TransparencyArchive full(TransparentOperation operation) {
		return archives.computeIfAbsent(operation, op -> switch (op) {
			case EXTRACT_FEATURES -> extractor();
			case PREPARE_PROBE -> probe();
			case COMPARE_CANDIDATE -> comparison();
			case DESERIALIZE_TEMPLATE -> deserialization();
		});
	}
	public static void log(Supplier<FingerprintTransparency> factory) {
		var candidate = new FingerprintTemplate(
			new FingerprintImage(TestResources.candidate()));
		try (var transparency = factory.get()) {
			new FingerprintMatcher(new FingerprintTemplate(new FingerprintImage(TestResources.probe()))).match(candidate);
		}
	}
	private static final Supplier<TransparencyArchive> universal = Suppliers.memoize(() -> {
		var buffer = new TransparencyBuffer();
		log(buffer::open);
		return buffer.toArchive();
	});
	public static TransparencyArchive universal() {
		return universal.get();
	}
}
