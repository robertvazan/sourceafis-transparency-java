// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class SkeletonMinutia {
	public final int offset;
	public final int x;
	public final int y;
	public final List<SkeletonRidge> ridges = new ArrayList<>();
	public SkeletonMinutia(int offset, IntPoint json) {
		this.offset = offset;
		x = json.x;
		y = json.y;
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
