// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

public interface SkeletonKey<T> extends TransparencyObjectKey<T> {
	String keyword();
	SkeletonType skeleton();
	@Override
	default String name() {
		return skeleton().name().toLowerCase() + "-" + keyword();
	}
}
