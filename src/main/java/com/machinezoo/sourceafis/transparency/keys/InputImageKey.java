// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

public record InputImageKey() implements EncodedImageKey, ContextKey<byte[]> {
	@Override
	public String name() {
		return "input-image";
	}
}
