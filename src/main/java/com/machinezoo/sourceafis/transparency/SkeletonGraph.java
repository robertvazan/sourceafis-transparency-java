// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static java.util.stream.Collectors.*;
import java.nio.*;
import java.util.*;
import java.util.function.*;
import com.machinezoo.sourceafis.transparency.formats.*;

public class SkeletonGraph {
	public final IntPoint size;
	public final List<SkeletonMinutia> minutiae = new ArrayList<>();
	public final List<SkeletonRidge> ridges = new ArrayList<>();
	public SkeletonGraph(IntPoint size) {
		this.size = size;
	}
	public static SkeletonGraph parse(Map<String, Supplier<byte[]>> bundle) {
		JsonSkeleton json = JsonSkeleton.parse(bundle);
		SkeletonGraph graph = new SkeletonGraph(new IntPoint(json.width, json.height));
		for (int i = 0; i < json.minutiae.size(); ++i)
			graph.minutiae.add(new SkeletonMinutia(i, json.minutiae.get(i)));
		ByteBuffer buffer = ByteBuffer.wrap(bundle.get(".dat").get());
		for (JsonSkeletonRidge jridge : json.ridges) {
			List<IntPoint> points = new ArrayList<>();
			for (int i = 0; i < jridge.length; ++i)
				points.add(new IntPoint(buffer.getInt(), buffer.getInt()));
			SkeletonRidge ridge = new SkeletonRidge(graph.minutiae.get(jridge.start), graph.minutiae.get(jridge.end), points);
			graph.ridges.add(ridge);
			ridge.start.add(ridge);
			ridge.end.add(ridge.opposite);
		}
		return graph;
	}
	public JsonSkeleton encode() {
		JsonSkeleton json = new JsonSkeleton();
		json.width = size.x;
		json.height = size.y;
		json.minutiae = minutiae.stream().map(SkeletonMinutia::position).collect(toList());
		json.ridges = ridges.stream().map(SkeletonRidge::encode).collect(toList());
		return json;
	}
	public static SkeletonGraph decode(JsonSkeleton json) {
		SkeletonGraph graph = new SkeletonGraph(new IntPoint(json.width, json.height));
		for (int i = 0; i < json.minutiae.size(); ++i)
			graph.minutiae.add(new SkeletonMinutia(i, json.minutiae.get(i)));
		for (JsonSkeletonRidge jridge : json.ridges) {
			SkeletonRidge ridge = new SkeletonRidge(graph.minutiae.get(jridge.start), graph.minutiae.get(jridge.end), jridge.points);
			graph.ridges.add(ridge);
			ridge.start.add(ridge);
			ridge.end.add(ridge.opposite);
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
