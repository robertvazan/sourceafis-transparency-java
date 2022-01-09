// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.util.*;

public record MinutiaPoint(IntPoint position, @Angle double direction, MinutiaType type) {
	public MinutiaPoint {
		Objects.requireNonNull(position, "Minutia has null position.");
		DoubleAngles.validate(direction);
		Objects.requireNonNull(type, "Minutia has null type.");
	}
}
