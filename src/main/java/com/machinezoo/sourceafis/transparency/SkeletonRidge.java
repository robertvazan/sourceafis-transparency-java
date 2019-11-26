// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class SkeletonRidge {
	public final SkeletonMinutia start;
	public final SkeletonMinutia end;
	public final List<IntPoint> points;
	public final SkeletonRidge opposite;
	public SkeletonRidge(SkeletonMinutia start, SkeletonMinutia end, List<IntPoint> points) {
		this.start = start;
		this.end = end;
		this.points = points;
		opposite = new SkeletonRidge(this);
	}
	private SkeletonRidge(SkeletonRidge opposite) {
		start = opposite.end;
		end = opposite.start;
		points = new ArrayList<>(opposite.points);
		Collections.reverse(points);
		this.opposite = opposite;
	}
}
