// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record OrthogonalSmoothingKey() implements SerializedObjectKey<DoubleMatrix> {
	@Override
	public String stem() {
		return "orthogonal-smoothing";
	}
	@Override
	public Class<DoubleMatrix> type() {
		return DoubleMatrix.class;
	}
}
