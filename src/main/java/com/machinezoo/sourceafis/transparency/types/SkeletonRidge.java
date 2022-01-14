// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;

public record SkeletonRidge(int start, int end, IntPoint[] points) implements Serializable {
	public SkeletonRidge {
		Validate.isTrue(start >= 0 && end >= 0, "Minutia offsets must be non-negative.");
		Objects.requireNonNull(points, "Missing list of ridge points.");
		for (var point : points)
			Objects.requireNonNull(point, "Null ridge point.");
	}
}
