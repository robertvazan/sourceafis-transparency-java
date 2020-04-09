// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class RawSkeletonGraph {
	public int width;
	public int height;
	public List<IntPoint> minutiae = new ArrayList<>();
	public List<RawSkeletonRidge> ridges = new ArrayList<>();
	public static RawSkeletonGraph parse(Map<String, Supplier<byte[]>> bundle) {
		return TransparencyArchive.parse(bundle.get(".cbor").get(), RawSkeletonGraph.class);
	}
}
