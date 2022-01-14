// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;

public record DoublePoint(double x, double y) implements Serializable {
	public @Angle double angle() {
		double angle = Math.atan2(y, x);
		return angle >= 0 ? angle : angle + 2 * Math.PI;
	}
}
