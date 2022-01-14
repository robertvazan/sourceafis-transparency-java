// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record CombinedMaskKey() implements SerializedObjectKey<BooleanMatrix> {
	@Override
	public String name() {
		return "combined-mask";
	}
	@Override
	public Class<BooleanMatrix> type() {
		return BooleanMatrix.class;
	}
}
