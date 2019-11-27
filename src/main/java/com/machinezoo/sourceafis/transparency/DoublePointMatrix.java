// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class DoublePointMatrix {
	public final int width;
	public final int height;
	private final double[] array;
	public DoublePointMatrix() {
		this(0, 0);
	}
	public DoublePointMatrix(int width, int height) {
		this.width = width;
		this.height = height;
		array = new double[2 * width * height];
	}
	public DoublePointMatrix(IntPoint size) {
		this(size.x, size.y);
	}
	public static DoublePointMatrix parse(Map<String, Supplier<byte[]>> bundle) {
		TransparencyArrayInfo info = TransparencyArrayInfo.parse(bundle);
		DoublePointMatrix matrix = new DoublePointMatrix(info.dimensions[1], info.dimensions[0]);
		ByteBuffer.wrap(bundle.get(".dat").get()).asDoubleBuffer().get(matrix.array);
		return matrix;
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public DoublePoint get(int x, int y) {
		int i = offset(x, y);
		return new DoublePoint(array[i], array[i + 1]);
	}
	public DoublePoint get(IntPoint at) {
		return get(at.x, at.y);
	}
	public void set(int x, int y, DoublePoint value) {
		int i = offset(x, y);
		array[i] = value.x;
		array[i + 1] = value.y;
	}
	public void set(IntPoint at, DoublePoint value) {
		set(at.x, at.y, value);
	}
	public DoubleStream stream() {
		return Arrays.stream(array);
	}
	private int offset(int x, int y) {
		return 2 * (y * width + x);
	}
}
