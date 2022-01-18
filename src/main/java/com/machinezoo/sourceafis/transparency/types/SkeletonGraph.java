// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;
import com.machinezoo.stagean.*;

public record SkeletonGraph(int width, int height, IntPoint[] minutiae, SkeletonRidge[] ridges) implements Serializable {
	@DraftCode("Implement full validation.")
	public SkeletonGraph {
		Validate.isTrue(width > 0 && height > 0, "Skeleton dimensions must be positive.");
		Objects.requireNonNull(minutiae, "Missing list of skeleton minutiae.");
		for (var point : minutiae)
			Objects.requireNonNull(point, "Null minutia position.");
		Objects.requireNonNull(ridges, "Missing list of skeleton ridges.");
		for (var ridge : ridges) {
			Objects.requireNonNull(ridge, "Null skeleton ridge.");
			Validate.isTrue(ridge.start() < minutiae.length && ridge.end() < minutiae.length, "Ridge references non-existent minutia.");
		}
	}
}
