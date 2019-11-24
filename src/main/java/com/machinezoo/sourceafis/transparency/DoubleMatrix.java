// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.util.*;
import java.util.function.*;
import com.machinezoo.sourceafis.transparency.formats.*;

public class DoubleMatrix {
	public final int width;
	public final int height;
	public DoubleSummaryStatistics stats;
	private final double[] array;
	public DoubleMatrix(int width, int height) {
		this.width = width;
		this.height = height;
		array = new double[width * height];
	}
	public DoubleMatrix(IntPoint size) {
		this(size.x, size.y);
	}
	public static DoubleMatrix parse(Map<String, Supplier<byte[]>> bundle) {
		JsonArrayInfo info = JsonArrayInfo.parse(bundle);
		DoubleMatrix matrix = new DoubleMatrix(info.dimensions[1], info.dimensions[0]);
		ByteBuffer.wrap(bundle.get(".dat").get()).asDoubleBuffer().get(matrix.array);
		matrix.stats = Arrays.stream(matrix.array).summaryStatistics();
		return matrix;
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
