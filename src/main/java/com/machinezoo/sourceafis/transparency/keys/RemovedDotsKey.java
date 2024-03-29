// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record RemovedDotsKey(SkeletonType skeleton) implements SkeletonKey<SkeletonGraph> {
	@Override
	public String stem() {
		return "removed-dots";
	}
	@Override
	public Class<SkeletonGraph> type() {
		return SkeletonGraph.class;
	}
}
