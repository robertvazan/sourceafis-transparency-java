// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class SkeletonMinutia {
	public final int x;
	public final int y;
	public final List<SkeletonRidge> ridges = new ArrayList<>();
	public SkeletonMinutia(IntPoint position) {
		x = position.x;
		y = position.y;
	}
	public IntPoint position() {
		return new IntPoint(x, y);
	}
	public DoublePoint center() {
		return position().center();
	}
	void add(SkeletonRidge ridge) {
		ridges.add(ridge);
	}
}
