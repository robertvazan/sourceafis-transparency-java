// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.util.*;
import java.util.function.*;

public class SkeletonGraph {
	public IntPoint size = new IntPoint();
	public List<SkeletonMinutia> minutiae = new ArrayList<>();
	public List<SkeletonRidge> ridges = new ArrayList<>();
	public static SkeletonGraph parse(Map<String, Supplier<byte[]>> bundle) {
		RawSkeletonGraph raw = RawSkeletonGraph.parse(bundle);
		SkeletonGraph graph = new SkeletonGraph();
		graph.size = new IntPoint(raw.width, raw.height);
		for (IntPoint position : raw.minutiae)
			graph.minutiae.add(new SkeletonMinutia(position));
		ByteBuffer buffer = ByteBuffer.wrap(bundle.get(".dat").get());
		for (RawSkeletonRidge jridge : raw.ridges) {
			List<IntPoint> points = new ArrayList<>();
			for (int i = 0; i < jridge.length; ++i)
				points.add(new IntPoint(buffer.getInt(), buffer.getInt()));
			SkeletonRidge ridge = new SkeletonRidge(graph.minutiae.get(jridge.start), graph.minutiae.get(jridge.end), points);
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
