// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;
import com.machinezoo.stagean.*;

public record BlockGrid(IntPoint blocks, IntPoint corners, int[] x, int[] y) implements Serializable {
	@DraftCode("Implement full validation.")
	public BlockGrid {
		Validate.isTrue(blocks.x() >= 0 && blocks.y() >= 0, "Block count must be non-negative.");
		Validate.isTrue(corners.x() == blocks.x() + 1 && corners.y() == blocks.y() + 1, "Corner count must be one higher than block count.");
		Objects.requireNonNull(x, "Missing X coordinates.");
		Objects.requireNonNull(y, "Missing Y coordinates.");
		Validate.isTrue(x.length == corners.x() && y.length == corners.y(), "Number of X and Y coordinates must match corner count.");
	}
	public IntPoint point(int atX, int atY) {
		return new IntPoint(x[atX], y[atY]);
	}
	public IntPoint point(IntPoint at) {
		return point(at.x(), at.y());
	}
	public IntRect block(int x, int y) {
		return IntRect.between(point(x, y), point(x + 1, y + 1));
	}
	public IntRect block(IntPoint at) {
		return block(at.x(), at.y());
	}
}
