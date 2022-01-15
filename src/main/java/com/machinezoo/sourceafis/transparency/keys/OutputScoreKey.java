// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;

public record OutputScoreKey() implements SerializedObjectKey<Double>, ContextKey<Double> {
	@Override
	public String stem() {
		return "output-score";
	}
	@Override
	public Class<Double> type() {
		return Double.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
}
