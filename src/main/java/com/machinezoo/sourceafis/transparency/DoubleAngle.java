// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class DoubleAngle {
	public static final double PI2 = 2 * Math.PI;
	public static double add(double start, double delta) {
		double angle = start + delta;
		return angle < PI2 ? angle : angle - PI2;
	}
	public static double opposite(double angle) {
		return angle < Math.PI ? angle + Math.PI : angle - Math.PI;
	}
	public static double complementary(double angle) {
		double complement = PI2 - angle;
		return complement < PI2 ? complement : complement - PI2;
	}
	public static DoublePoint toVector(double angle) {
		return new DoublePoint(Math.cos(angle), Math.sin(angle));
	}
	public static double atan(DoublePoint vector) {
		double angle = Math.atan2(vector.y, vector.x);
		return angle >= 0 ? angle : angle + PI2;
	}
	public static double degrees(double angle) {
		return angle / (2 * Math.PI) * 360;
	}
	public static double fromOrientation(double angle) {
		return 0.5 * angle;
	}
	public static double normalize(double angle) {
		while (angle >= PI2)
			angle -= PI2;
		while (angle < 0)
			angle += PI2;
		return angle;
	}
	public static double distance(double first, double second) {
		double delta = Math.abs(first - second);
		return delta <= Math.PI ? delta : PI2 - delta;
	}
	public static double difference(double first, double second) {
		double angle = first - second;
		return angle >= 0 ? angle : angle + PI2;
	}
}
