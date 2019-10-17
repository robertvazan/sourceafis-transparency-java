// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.util.*;

public class SkeletonRidge {
	public final SkeletonMinutia start;
	public final SkeletonMinutia end;
	public final List<IntPoint> points;
	public final boolean reversed;
	public final SkeletonRidge opposite;
	SkeletonRidge(JsonSkeletonRidge json, List<SkeletonMinutia> minutiae, ByteBuffer buffer) {
		start = minutiae.get(json.start);
		end = minutiae.get(json.end);
		if (buffer != null) {
			points = new ArrayList<>();
			for (int i = 0; i < json.length; ++i)
				points.add(new IntPoint(buffer.getInt(), buffer.getInt()));
		} else
			points = new ArrayList<>(json.points);
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
