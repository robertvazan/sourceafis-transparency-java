// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record BlockOrientationKey() implements SerializedObjectKey<DoublePointMatrix> {
	@Override
	public String name() {
		return "block-orientation";
	}
	@Override
	public Class<DoublePointMatrix> type() {
		return DoublePointMatrix.class;
	}
}
