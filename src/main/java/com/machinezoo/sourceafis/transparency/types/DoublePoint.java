// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

public record DoublePoint(double x, double y) {
	public @Angle double angle() {
		double angle = Math.atan2(y, x);
		return angle >= 0 ? angle : angle + 2 * Math.PI;
	}
}
