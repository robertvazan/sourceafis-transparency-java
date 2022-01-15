// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;

public record CandidateImageKey() implements EncodedImageKey, ContextKey<byte[]> {
	@Override
	public String stem() {
		return "candidate-image";
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
}
