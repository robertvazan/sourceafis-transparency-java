// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record InnerMaskKey() implements SerializedObjectKey<BooleanMatrix> {
	@Override
	public String stem() {
		return "inner-mask";
	}
	@Override
	public Class<BooleanMatrix> type() {
		return BooleanMatrix.class;
	}
}
