// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import org.apache.commons.lang3.*;

public class DoubleAngles {
	private static final double PI2 = 2 * Math.PI;
	public static void validate(@Angle double angle) {
		Validate.isTrue(angle >= 0 && angle <= PI2, "Angle is out of range.");
	}
	public static double degrees(@Angle double angle) {
		return angle / (2 * Math.PI) * 360;
	}
	public static DoublePoint toVector(@Angle double angle) {
		return new DoublePoint(Math.cos(angle), Math.sin(angle));
	}
	public static @OrientationAngle double toOrientation(@Angle double angle) {
		return angle < Math.PI ? 2 * angle : 2 * (angle - Math.PI);
	}
	public static @Angle double fromOrientation(@OrientationAngle double angle) {
		return 0.5 * angle;
	}
	public static @Angle double opposite(@Angle double angle) {
		return angle < Math.PI ? angle + Math.PI : angle - Math.PI;
	}
}
