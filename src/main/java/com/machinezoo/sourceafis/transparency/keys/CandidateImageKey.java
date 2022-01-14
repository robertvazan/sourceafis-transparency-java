// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

public record CandidateImageKey() implements EncodedImageKey, ContextKey<byte[]> {
	@Override
	public String name() {
		return "candidate-image";
	}
}
