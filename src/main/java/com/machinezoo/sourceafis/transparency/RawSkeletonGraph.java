// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class RawSkeletonGraph {
	public int width;
	public int height;
	public List<IntPoint> minutiae = new ArrayList<>();
	public List<RawSkeletonRidge> ridges = new ArrayList<>();
	public static RawSkeletonGraph parse(Map<String, Supplier<byte[]>> bundle) {
		return new Gson().fromJson(new String(bundle.get(".json").get(), StandardCharsets.UTF_8), RawSkeletonGraph.class);
	}
}
