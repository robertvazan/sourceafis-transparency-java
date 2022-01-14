// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

public interface SkeletonKey<T> extends SerializedObjectKey<T> {
	SkeletonType skeleton();
	@Override
	default String name() {
		return skeleton().name().toLowerCase() + "-" + stem();
	}
}
