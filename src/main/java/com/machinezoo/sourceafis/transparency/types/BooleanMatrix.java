// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.util.*;
import org.apache.commons.lang3.*;

public record BooleanMatrix(int width, int height, boolean[] cells) {
	public BooleanMatrix {
		Validate.isTrue(width >= 0 && height >= 0, "Matrix dimensions must be non-negative.");
		Objects.requireNonNull(cells, "Matrix cells must be non-null.");
		Validate.isTrue(cells.length == width * height, "Matrix array length does not match matrix dimensions.");
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public boolean get(int x, int y) {
		return cells[offset(x, y)];
	}
	public boolean get(IntPoint at) {
		return get(at.x(), at.y());
	}
	private int offset(int x, int y) {
		return y * width + x;
	}
	public BooleanMatrix expand(BlockMap blocks) {
		var expanded = new boolean[blocks.pixels().x() * blocks.pixels().y()];
		for (int by = 0; by < blocks.primary().blocks().y(); ++by) {
			for (int bx = 0; bx < blocks.primary().blocks().x(); ++bx) {
				if (get(bx, by)) {
					var rect = blocks.primary().block(bx, by);
					for (int y = rect.top(); y < rect.bottom(); ++y)
						for (int x = rect.left(); x < rect.right(); ++x)
							expanded[y * blocks.pixels().x() + x] = true;
				}
			}
		}
		return new BooleanMatrix(blocks.pixels().x(), blocks.pixels().y(), expanded);
	}
}
