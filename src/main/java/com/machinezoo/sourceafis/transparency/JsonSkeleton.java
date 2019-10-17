// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class JsonSkeleton {
	public int width;
	public int height;
	public List<IntPoint> minutiae;
	public List<JsonSkeletonRidge> ridges;
	public SkeletonGraph decode() {
		return new SkeletonGraph(this);
	}
}
