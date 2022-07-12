// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import java.util.*;

public record MinutiaPoint(IntPoint position, @Angle float direction, MinutiaType type) implements Serializable {
	public MinutiaPoint {
		Objects.requireNonNull(position, "Minutia has null position.");
		FloatAngles.validate(direction);
		Objects.requireNonNull(type, "Minutia has null type.");
	}
}
