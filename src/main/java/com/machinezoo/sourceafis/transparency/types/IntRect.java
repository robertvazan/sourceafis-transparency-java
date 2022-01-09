// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

public record IntRect(int x, int y, int width, int height) {
	/*
	 * Width/height may be negative, in which case the rect is empty.
	 */
	public IntRect {
	}
	public int left() {
		return x;
	}
	public int top() {
		return y;
	}
	public int right() {
		return x + width;
	}
	public int bottom() {
		return y + height;
	}
	public static IntRect between(int startX, int startY, int endX, int endY) {
		return new IntRect(startX, startY, endX - startX, endY - startY);
	}
	public static IntRect between(IntPoint start, IntPoint end) {
		return between(start.x(), start.y(), end.x(), end.y());
	}
}
