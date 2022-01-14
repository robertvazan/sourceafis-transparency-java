// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record CandidateGrayscaleKey() implements SerializedObjectKey<GrayscaleImage>, ContextKey<GrayscaleImage> {
	@Override
	public String stem() {
		return "candidate-grayscale";
	}
	@Override
	public Class<GrayscaleImage> type() {
		return GrayscaleImage.class;
	}
}
