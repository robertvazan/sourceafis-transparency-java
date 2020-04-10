// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class DoubleMatrix {
	public final int width;
	public final int height;
	private final double[] cells;
	public DoubleMatrix(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new double[width * height];
	}
	public DoubleMatrix() {
		this(0, 0);
	}
	public DoubleMatrix(IntPoint size) {
		this(size.x, size.y);
	}
	public static DoubleMatrix parse(Map<String, Supplier<byte[]>> bundle) {
		return TransparencyArchive.parse(bundle.get(".cbor").get(), DoubleMatrix.class);
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public double get(int x, int y) {
		return cells[offset(x, y)];
	}
	public double get(IntPoint at) {
		return get(at.x, at.y);
	}
	public void set(int x, int y, double value) {
		cells[offset(x, y)] = value;
	}
	public void set(IntPoint at, double value) {
		set(at.x, at.y, value);
	}
	private int offset(int x, int y) {
		return y * width + x;
	}
	public DoubleStream stream() {
		return Arrays.stream(cells);
	}
}
