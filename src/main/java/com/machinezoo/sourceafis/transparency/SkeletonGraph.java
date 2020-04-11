// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class SkeletonGraph {
	public IntPoint size = new IntPoint();
	public List<SkeletonMinutia> minutiae = new ArrayList<>();
	public List<SkeletonRidge> ridges = new ArrayList<>();
	public static SkeletonGraph parse(byte[] data) {
		RawSkeletonGraph raw = RawSkeletonGraph.parse(data);
		SkeletonGraph graph = new SkeletonGraph();
		graph.size = new IntPoint(raw.width, raw.height);
		for (IntPoint position : raw.minutiae)
			graph.minutiae.add(new SkeletonMinutia(position));
		for (RawSkeletonRidge rridge : raw.ridges) {
			SkeletonRidge ridge = new SkeletonRidge(graph.minutiae.get(rridge.start), graph.minutiae.get(rridge.end), rridge.points);
			graph.ridges.add(ridge);
			ridge.start.ridges.add(ridge);
			ridge.end.ridges.add(ridge.opposite);
		}
		return graph;
	}
	public BooleanMatrix shadow() {
		BooleanMatrix map = new BooleanMatrix(size);
		for (SkeletonRidge ridge : ridges)
			for (IntPoint point : ridge.points)
				map.set(point, true);
		for (SkeletonMinutia minutia : minutiae)
			map.set(minutia.position(), true);
		return map;
	}
}
