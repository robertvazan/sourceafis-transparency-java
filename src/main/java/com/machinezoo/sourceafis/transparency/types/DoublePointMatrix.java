// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.util.*;
import org.apache.commons.lang3.*;

public record DoublePointMatrix(int width, int height, double[] vectors) {
	public DoublePointMatrix {
		Validate.isTrue(width >= 0, "Matrix width must be non-negative.");
		Validate.isTrue(height >= 0, "Matrix width must be non-negative.");
		Objects.requireNonNull(vectors, "Matrix cells must be non-null.");
		Validate.isTrue(vectors.length == 2 * width * height, "Matrix array length does not match matrix dimensions.");
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public DoublePoint get(int x, int y) {
		int i = offset(x, y);
		return new DoublePoint(vectors[i], vectors[i + 1]);
	}
	public DoublePoint get(IntPoint at) {
		return get(at.x(), at.y());
	}
	private int offset(int x, int y) {
		return 2 * (y * width + x);
	}
}
