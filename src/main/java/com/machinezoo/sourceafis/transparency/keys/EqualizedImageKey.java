// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record EqualizedImageKey() implements SerializedObjectKey<DoubleMatrix> {
	@Override
	public String stem() {
		return "equalized-image";
	}
	@Override
	public Class<DoubleMatrix> type() {
		return DoubleMatrix.class;
	}
}
