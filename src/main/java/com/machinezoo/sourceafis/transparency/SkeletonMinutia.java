// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class SkeletonMinutia {
	public int x;
	public int y;
	public List<SkeletonRidge> ridges = new ArrayList<>();
	public SkeletonMinutia() {
	}
	public SkeletonMinutia(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public SkeletonMinutia(IntPoint position) {
		this(position.x, position.y);
	}
	public IntPoint position() {
		return new IntPoint(x, y);
	}
	public DoublePoint center() {
		return position().center();
	}
}
