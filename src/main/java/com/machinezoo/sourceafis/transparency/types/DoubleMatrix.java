// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;

public record DoubleMatrix(int width, int height, double[] cells) implements Serializable {
	public DoubleMatrix {
		Validate.isTrue(width > 0 && height > 0, "Matrix dimensions must be positive.");
		Objects.requireNonNull(cells, "Matrix cells must be non-null.");
		Validate.isTrue(cells.length == width * height, "Matrix array length does not match matrix dimensions.");
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public double get(int x, int y) {
		return cells[offset(x, y)];
	}
	public double get(IntPoint at) {
		return get(at.x(), at.y());
	}
	private int offset(int x, int y) {
		return y * width + x;
	}
}
