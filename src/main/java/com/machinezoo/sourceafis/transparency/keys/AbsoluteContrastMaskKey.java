// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record AbsoluteContrastMaskKey() implements TransparencyObjectKey<BooleanMatrix> {
	@Override
	public String name() {
		return "absolute-contrast-mask";
	}
	@Override
	public Class<BooleanMatrix> type() {
		return BooleanMatrix.class;
	}
}