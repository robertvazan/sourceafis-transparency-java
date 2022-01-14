// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.*;

public interface EncodedImageKey extends TransparencyKey<byte[]> {
	@Override
	default String mime() {
		return "image/*";
	}
	@Override
	default byte[] deserialize(String mime, byte[] data) {
		return data;
	}
	@Override
	default byte[] serialize(String mime, byte[] object) {
		return switch (mime) {
			case "image/*", "*/*" -> object;
			default -> throw new IllegalArgumentException();
		};
	}
}
