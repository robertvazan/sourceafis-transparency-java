// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.context;

import com.machinezoo.stagean.*;

public interface ContextImageKey extends TransparencyContextKey<byte[]> {
	@Override
	default String mime() {
		return "image/*";
	}
	@Override
	@DraftCode("Support encoding into specific image formats based on supplied MIME type.")
	default byte[] deserialize(String mime, byte[] data) {
		return switch (mime) {
			case "image/*" -> data;
			default -> throw new IllegalArgumentException();
		};
	}
	@Override
	default byte[] serialize(String mime, byte[] object) {
		if (!mime.startsWith("image/"))
			throw new IllegalArgumentException();
		return object;
	}
}
