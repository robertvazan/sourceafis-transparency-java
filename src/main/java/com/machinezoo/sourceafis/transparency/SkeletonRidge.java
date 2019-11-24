// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import com.machinezoo.sourceafis.transparency.formats.*;

public class SkeletonRidge {
	public final SkeletonMinutia start;
	public final SkeletonMinutia end;
	public final List<IntPoint> points;
	public final boolean reversed;
	public final SkeletonRidge opposite;
	SkeletonRidge(SkeletonMinutia start, SkeletonMinutia end, List<IntPoint> points) {
		this.start = start;
		this.end = end;
		this.points = points;
		reversed = false;
		opposite = new SkeletonRidge(this);
	}
	SkeletonRidge(SkeletonRidge opposite) {
		start = opposite.end;
		end = opposite.start;
		points = new ArrayList<>(opposite.points);
		Collections.reverse(points);
		reversed = true;
		this.opposite = opposite;
	}
	JsonSkeletonRidge encode() {
		JsonSkeletonRidge json = new JsonSkeletonRidge();
		json.start = start.offset;
		json.end = end.offset;
		json.length = points.size();
		json.points = new ArrayList<>(points);
		return json;
	}
}
