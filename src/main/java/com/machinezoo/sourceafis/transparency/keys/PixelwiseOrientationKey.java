// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record PixelwiseOrientationKey() implements TransparencyObjectKey<DoublePointMatrix> {
	@Override
	public String name() {
		return "pixelwise-orientation";
	}
	@Override
	public Class<DoublePointMatrix> type() {
		return DoublePointMatrix.class;
	}
}
