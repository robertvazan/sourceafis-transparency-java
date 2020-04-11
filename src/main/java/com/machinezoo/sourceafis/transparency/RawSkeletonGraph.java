// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class RawSkeletonGraph {
	public int width;
	public int height;
	public List<IntPoint> minutiae = new ArrayList<>();
	public List<RawSkeletonRidge> ridges = new ArrayList<>();
	public static RawSkeletonGraph parse(byte[] data) {
		return TransparencyArchive.parse(data, RawSkeletonGraph.class);
	}
}
