package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.util.*;
import java.util.function.*;

public class HistogramVolume {
	public final int width;
	public final int height;
	public final int depth;
	private final int[] array;
	public HistogramVolume(int width, int height, int depth, byte[] buffer) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		array = new int[width * height * depth];
		ByteBuffer.wrap(buffer).asIntBuffer().get(array);
	}
	public HistogramVolume(TransparencyArrayInfo info, byte[] data) {
		this(info.dimensions[1], info.dimensions[0], info.dimensions[2], data);
	}
	public HistogramVolume(Map<String,Supplier<byte[]>> bundle) {
		this(TransparencyArrayInfo.parse(bundle.get(".json").get()), bundle.get(".dat").get());
	}
	public int get(int x, int y, int z) {
		return array[offset(x, y, z)];
	}
	public int get(IntPoint at, int z) {
		return get(at.x, at.y, z);
	}
	private int offset(int x, int y, int z) {
		return (y * width + x) * depth + z;
	}
}
