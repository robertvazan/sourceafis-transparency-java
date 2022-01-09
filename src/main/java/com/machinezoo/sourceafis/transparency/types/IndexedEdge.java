// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import org.apache.commons.lang3.*;

public record IndexedEdge(int length, @Angle double referenceAngle, @Angle double neighborAngle, int reference, int neighbor) implements EdgeShape {
	public IndexedEdge {
		Validate.isTrue(length >= 0, "Edge length must be non-negative.");
		DoubleAngles.validate(referenceAngle);
		DoubleAngles.validate(neighborAngle);
		Validate.isTrue(reference >= 0 && neighbor >= 0, "Reference and neighbor minutia offsets must be non-negative.");
	}
}
