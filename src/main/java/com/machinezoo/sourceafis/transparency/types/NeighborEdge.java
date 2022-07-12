// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import org.apache.commons.lang3.*;

public record NeighborEdge(short length, @Angle float referenceAngle, @Angle float neighborAngle, short neighbor) implements EdgeShape {
	public NeighborEdge {
		Validate.isTrue(length >= 0, "Edge length must be non-negative.");
		FloatAngles.validate(referenceAngle);
		FloatAngles.validate(neighborAngle);
		Validate.isTrue(neighbor >= 0, "Neighbor minutia offset must be non-negative.");
	}
}
