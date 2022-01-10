// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record BinarizedSkeletonKey(SkeletonType skeleton) implements SkeletonKey<BooleanMatrix> {
	@Override
	public String keyword() {
		return "binarized-skeleton";
	}
	@Override
	public Class<BooleanMatrix> type() {
		return BooleanMatrix.class;
	}
}
