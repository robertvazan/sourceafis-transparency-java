// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import org.apache.commons.lang3.*;

public class FloatAngles {
	private static final float PI2 = (float)(2 * Math.PI);
	public static void validate(@Angle float angle) {
		Validate.isTrue(angle >= 0 && angle <= PI2, "Angle is out of range.");
	}
	public static float degrees(@Angle float angle) {
		return angle / PI2 * 360;
	}
}
