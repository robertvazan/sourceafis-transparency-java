// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record ScoreKey() implements SerializedObjectKey<ScoreBreakdown> {
	@Override
	public String stem() {
		return "score";
	}
	@Override
	public Class<ScoreBreakdown> type() {
		return ScoreBreakdown.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
}
