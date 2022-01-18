// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;

public record HistogramCube(int width, int height, int bins, int[] counts) implements Serializable {
	public HistogramCube {
		Validate.isTrue(width > 0 && height > 0 && bins > 0, "Cube dimensions must be positive.");
		Objects.requireNonNull(counts, "Missing cube array.");
		Validate.isTrue(counts.length == width * height * bins, "Cube array length must match cube dimensions.");
	}
	public int get(int x, int y, int z) {
		return counts[offset(x, y, z)];
	}
	public int get(IntPoint at, int z) {
		return get(at.x(), at.y(), z);
	}
	private int offset(int x, int y, int z) {
		return (y * width + x) * bins + z;
	}
}
