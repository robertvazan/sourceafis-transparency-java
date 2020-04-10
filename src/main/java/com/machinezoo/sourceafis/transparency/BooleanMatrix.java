// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class BooleanMatrix {
	public final int width;
	public final int height;
	private final boolean[] cells;
	public BooleanMatrix(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new boolean[width * height];
	}
	public BooleanMatrix() {
		this(0, 0);
	}
	public BooleanMatrix(IntPoint size) {
		this(size.x, size.y);
	}
	public static BooleanMatrix parse(Map<String, Supplier<byte[]>> bundle) {
		return TransparencyArchive.parse(bundle.get(".cbor").get(), BooleanMatrix.class);
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public boolean get(int x, int y) {
		return cells[offset(x, y)];
	}
	public boolean get(IntPoint at) {
		return get(at.x, at.y);
	}
	public boolean get(int x, int y, boolean fallback) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return fallback;
		return cells[offset(x, y)];
	}
	public boolean get(IntPoint at, boolean fallback) {
		return get(at.x, at.y, fallback);
	}
	public void set(int x, int y, boolean value) {
		cells[offset(x, y)] = value;
	}
	public void set(IntPoint at, boolean value) {
		set(at.x, at.y, value);
	}
	public BooleanMatrix expand(BlockMap blocks) {
		BooleanMatrix expanded = new BooleanMatrix(blocks.pixels.x, blocks.pixels.y);
		for (IntPoint at : blocks.primary.blocks)
			if (get(at))
				for (IntPoint pixel : blocks.primary.block(at))
					expanded.set(pixel, true);
		return expanded;
	}
	private int offset(int x, int y) {
		return y * width + x;
	}
}
