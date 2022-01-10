// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record ThinnedSkeletonKey(SkeletonType skeleton) implements SkeletonKey<BooleanMatrix> {
	@Override
	public String keyword() {
		return "thinned-skeleton";
	}
	@Override
	public Class<BooleanMatrix> type() {
		return BooleanMatrix.class;
	}
}
