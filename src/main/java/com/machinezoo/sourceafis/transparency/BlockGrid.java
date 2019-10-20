// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class BlockGrid {
	public IntPoint blocks;
	public IntPoint corners;
	public int[] x;
	public int[] y;
	public IntPoint point(int atX, int atY) {
		return new IntPoint(x[atX], y[atY]);
	}
	public IntPoint point(IntPoint at) {
		return point(at.x, at.y);
	}
	public IntRect block(int x, int y) {
		return IntRect.between(point(x, y), point(x + 1, y + 1));
	}
	public IntRect block(IntPoint at) {
		return block(at.x, at.y);
	}
}
