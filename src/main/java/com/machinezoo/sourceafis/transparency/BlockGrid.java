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
	public IntBlock block(int x, int y) {
		return IntBlock.between(point(x, y), point(x + 1, y + 1));
	}
	public IntBlock block(IntPoint at) {
		return block(at.x, at.y);
	}
}
