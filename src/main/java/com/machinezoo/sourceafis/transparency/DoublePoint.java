// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class DoublePoint {
	public double x;
	public double y;
	public DoublePoint() {
	}
	public DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public DoublePoint add(DoublePoint other) {
		return new DoublePoint(x + other.x, y + other.y);
	}
	public DoublePoint minus(DoublePoint other) {
		return new DoublePoint(x - other.x, y - other.y);
	}
	public DoublePoint negate() {
		return new DoublePoint(-x, -y);
	}
	public DoublePoint multiply(double factor) {
		return new DoublePoint(factor * x, factor * y);
	}
	public boolean isZero() {
		return x == 0 && y == 0;
	}
	public double length() {
		return Math.sqrt(x * x + y * y);
	}
	public DoublePoint normalize() {
		return multiply(1.0 / length());
	}
	public DoublePoint center() {
		return new DoublePoint(x + 0.5, y + 0.5);
	}
	public IntPoint floor() {
		return new IntPoint((int)Math.floor(x), (int)Math.floor(y));
	}
	public double angle() {
		double angle = Math.atan2(y, x);
		return angle >= 0 ? angle : angle + 2 * Math.PI;
	}
	public double multiply(DoublePoint other) {
		return x * other.x + y * other.y;
	}
}
