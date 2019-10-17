package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.util.*;
import java.util.function.*;

public class DoubleMap {
	public final int width;
	public final int height;
	public final DoubleSummaryStatistics stats;
	private final double[] array;
	public DoubleMap(int width, int height, byte[] buffer) {
		this.width = width;
		this.height = height;
		array = new double[width * height];
		ByteBuffer.wrap(buffer).asDoubleBuffer().get(array);
		stats = Arrays.stream(array).summaryStatistics();
	}
	public DoubleMap(TransparencyArrayInfo info, byte[] data) {
		this(info.dimensions[1], info.dimensions[0], data);
	}
	public DoubleMap(Map<String,Supplier<byte[]>> bundle) {
		this(TransparencyArrayInfo.parse(bundle.get(".json").get()), bundle.get(".dat").get());
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public double get(int x, int y) {
		return array[offset(x, y)];
	}
	public double get(IntPoint at) {
		return get(at.x, at.y);
	}
	private int offset(int x, int y) {
		return y * width + x;
	}
}
