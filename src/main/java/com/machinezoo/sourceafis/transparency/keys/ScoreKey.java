// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record ScoreKey() implements TransparencyObjectKey<ScoreBreakdown> {
	@Override
	public String name() {
		return "score";
	}
	@Override
	public Class<ScoreBreakdown> type() {
		return ScoreBreakdown.class;
	}
}
