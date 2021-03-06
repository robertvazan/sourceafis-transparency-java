// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.stream.*;

public class DoublePointMatrix {
	public final int width;
	public final int height;
	private final double[] vectors;
	public DoublePointMatrix(int width, int height) {
		this.width = width;
		this.height = height;
		vectors = new double[2 * width * height];
	}
	public DoublePointMatrix() {
		this(0, 0);
	}
	public DoublePointMatrix(IntPoint size) {
		this(size.x, size.y);
	}
	public static DoublePointMatrix parse(byte[] data) {
		return TransparencyArchive.parse(data, DoublePointMatrix.class);
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public DoublePoint get(int x, int y) {
		int i = offset(x, y);
		return new DoublePoint(vectors[i], vectors[i + 1]);
	}
	public DoublePoint get(IntPoint at) {
		return get(at.x, at.y);
	}
	public void set(int x, int y, DoublePoint value) {
		int i = offset(x, y);
		vectors[i] = value.x;
		vectors[i + 1] = value.y;
	}
	public void set(IntPoint at, DoublePoint value) {
		set(at.x, at.y, value);
	}
	public DoubleStream stream() {
		return Arrays.stream(vectors);
	}
	private int offset(int x, int y) {
		return 2 * (y * width + x);
	}
}
