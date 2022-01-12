// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.utils.*;

public interface TransparencyObjectKey<T> extends TransparencyKey<T> {
	Class<T> type();
	@Override
	default String mime() {
		return "application/cbor";
	}
	@Override
	default T deserialize(String mime, byte[] data) {
		return switch (mime) {
			case "application/cbor" -> CborUtils.deserialize(data, type());
			default -> throw new IllegalArgumentException();
		};
	}
	@Override
	default byte[] serialize(String mime, T object) {
		return switch (mime) {
			case "application/cbor", "application/*", "*/*" -> CborUtils.serialize(object);
			default -> throw new IllegalArgumentException();
		};
	}
}
