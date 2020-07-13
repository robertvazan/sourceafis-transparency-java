// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class TransparencyPath {
	private final String keyword;
	public String keyword() {
		return keyword;
	}
	private final SkeletonType skeleton;
	public SkeletonType skeleton() {
		return skeleton;
	}
	public String key() {
		if (skeleton != null)
			return skeleton.prefix() + "-" + keyword;
		return keyword;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TransparencyPath))
			return false;
		TransparencyPath other = (TransparencyPath)obj;
		return Objects.equals(keyword, other.keyword) && skeleton == other.skeleton;
	}
	@Override
	public int hashCode() {
		return Objects.hash(keyword, skeleton);
	}
	public TransparencyPath(SkeletonType skeleton, String keyword) {
		this.skeleton = skeleton;
		this.keyword = keyword;
	}
	public TransparencyPath(String key) {
		if (key.startsWith("ridges-")) {
			skeleton = SkeletonType.RIDGES;
			keyword = key.substring("ridges-".length());
		} else if (key.startsWith("valleys-")) {
			skeleton = SkeletonType.VALLEYS;
			keyword = key.substring("valleys-".length());
		} else {
			skeleton = null;
			keyword = key;
		}
	}
	@Override
	public String toString() {
		return key();
	}
}
