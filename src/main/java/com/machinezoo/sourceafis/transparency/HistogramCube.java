// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class HistogramCube {
	public final int width;
	public final int height;
	public final int bins;
	private final int[] counts;
	public HistogramCube(int width, int height, int bins) {
		this.width = width;
		this.height = height;
		this.bins = bins;
		counts = new int[width * height * bins];
	}
	public HistogramCube() {
		this(0, 0, 0);
	}
	public HistogramCube(IntPoint size, int bins) {
		this(size.x, size.y, bins);
	}
	public static HistogramCube parse(Map<String, Supplier<byte[]>> bundle) {
		return TransparencyArchive.parse(bundle.get(".cbor").get(), HistogramCube.class);
	}
	public int get(int x, int y, int z) {
		return counts[offset(x, y, z)];
	}
	public int get(IntPoint at, int z) {
		return get(at.x, at.y, z);
	}
	public void set(int x, int y, int z, int value) {
		counts[offset(x, y, z)] = value;
	}
	public void set(IntPoint at, int z, int value) {
		set(at.x, at.y, z, value);
	}
	private int offset(int x, int y, int z) {
		return (y * width + x) * bins + z;
	}
}
