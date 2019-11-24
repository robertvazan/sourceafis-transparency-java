// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class TransparencyPath {
	private final String stage;
	public String stage() {
		return stage;
	}
	private final SkeletonType skeleton;
	public SkeletonType skeleton() {
		return skeleton;
	}
	public String keyword() {
		if (skeleton != null)
			return skeleton.prefix() + "-" + stage;
		return stage;
	}
	private final String suffix;
	public String suffix() {
		return suffix;
	}
	@Override public boolean equals(Object obj) {
		if (!(obj instanceof TransparencyPath))
			return false;
		TransparencyPath other = (TransparencyPath)obj;
		return Objects.equals(stage, other.stage) && skeleton == other.skeleton && Objects.equals(suffix, other.suffix);
	}
	@Override public int hashCode() {
		return Objects.hash(stage, skeleton, suffix);
	}
	public TransparencyPath(SkeletonType skeleton, String stage, String suffix) {
		this.skeleton = skeleton;
		this.stage = stage;
		this.suffix = suffix;
	}
	public TransparencyPath(String keyword, String suffix) {
		if (keyword.startsWith("ridges-")) {
			skeleton = SkeletonType.RIDGES;
			stage = keyword.substring("ridges-".length());
		} else if (keyword.startsWith("valleys-")) {
			skeleton = SkeletonType.VALLEYS;
			stage = keyword.substring("valleys-".length());
		} else {
			skeleton = null;
			stage = keyword;
		}
		this.suffix = suffix;
	}
}
