// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.util.*;
import java.util.function.*;

public class HistogramCube {
	public final int width;
	public final int height;
	public final int depth;
	private final int[] array;
	public HistogramCube(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		array = new int[width * height * depth];
	}
	public HistogramCube(IntPoint size, int depth) {
		this(size.x, size.y, depth);
	}
	public static HistogramCube parse(Map<String, Supplier<byte[]>> bundle) {
		TransparencyArrayInfo info = TransparencyArrayInfo.parse(bundle);
		HistogramCube cube = new HistogramCube(info.dimensions[1], info.dimensions[0], info.dimensions[2]);
		ByteBuffer.wrap(bundle.get(".dat").get()).asIntBuffer().get(cube.array);
		return cube;
	}
	public int get(int x, int y, int z) {
		return array[offset(x, y, z)];
	}
	public int get(IntPoint at, int z) {
		return get(at.x, at.y, z);
	}
	public void set(int x, int y, int z, int value) {
		array[offset(x, y, z)] = value;
	}
	public void set(IntPoint at, int z, int value) {
		set(at.x, at.y, z, value);
	}
	private int offset(int x, int y, int z) {
		return (y * width + x) * depth + z;
	}
}
