// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.context;

public interface ContextImageKey extends TransparencyContextKey<byte[]> {
	@Override
	default byte[] deserialize(String mime, byte[] data) {
		return switch (mime) {
			case "image/*" -> data;
			default -> throw new IllegalArgumentException();
		};
	}
}
