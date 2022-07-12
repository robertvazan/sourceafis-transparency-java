// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import org.apache.commons.lang3.*;

public record IndexedEdge(short length, @Angle float referenceAngle, @Angle float neighborAngle, short reference, short neighbor) implements EdgeShape {
	public IndexedEdge {
		Validate.isTrue(length >= 0, "Edge length must be non-negative.");
		FloatAngles.validate(referenceAngle);
		FloatAngles.validate(neighborAngle);
		Validate.isTrue(reference >= 0 && neighbor >= 0, "Reference and neighbor minutia offsets must be non-negative.");
	}
}
