// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static java.util.stream.Collectors.*;
import java.nio.*;
import java.util.*;
import java.util.function.*;

public class SkeletonGraph {
	public final IntPoint size;
	public final List<SkeletonMinutia> minutiae;
	public final List<SkeletonRidge> ridges;
	public SkeletonGraph(JsonSkeleton json, byte[] array) {
		size = new IntPoint(json.width, json.height);
		minutiae = new ArrayList<>();
		for (int i = 0; i < json.minutiae.size(); ++i)
			minutiae.add(new SkeletonMinutia(i, json.minutiae.get(i)));
		ByteBuffer buffer = array != null ? ByteBuffer.wrap(array) : null;
		ridges = json.ridges.stream().map(r -> new SkeletonRidge(r, minutiae, buffer)).collect(toList());
		for (SkeletonRidge ridge : ridges) {
			ridge.start.add(ridge);
			ridge.end.add(ridge.opposite);
		}
	}
	public SkeletonGraph(JsonSkeleton json) {
		this(json, null);
	}
	public SkeletonGraph(Map<String, Supplier<byte[]>> bundle) {
		this(TransparencyUtils.json(bundle.get(".json").get(), JsonSkeleton.class), bundle.get(".dat").get());
	}
	public JsonSkeleton encode() {
		JsonSkeleton json = new JsonSkeleton();
		json.width = size.x;
		json.height = size.y;
		json.minutiae = minutiae.stream().map(SkeletonMinutia::position).collect(toList());
		json.ridges = ridges.stream().map(SkeletonRidge::encode).collect(toList());
		return json;
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
