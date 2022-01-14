// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

public record ProbeImageKey() implements EncodedImageKey, ContextKey<byte[]> {
	@Override
	public String stem() {
		return "probe-image";
	}
}