// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;
import com.machinezoo.sourceafis.transparency.formats.*;

public class BooleanMatrix {
	public final int width;
	public final int height;
	private final boolean[] array;
	public BooleanMatrix() {
		this(0, 0);
	}
	public BooleanMatrix(int width, int height) {
		this.width = width;
		this.height = height;
		array = new boolean[width * height];
	}
	public BooleanMatrix(IntPoint size) {
		this(size.x, size.y);
	}
	public BooleanMatrix(int width, int height, byte[] buffer) {
		this(width, height);
		for (int i = 0; i < array.length; ++i)
			array[i] = buffer[i] != 0;
	}
	public BooleanMatrix(JsonArrayInfo info, byte[] data) {
		this(info.dimensions[1], info.dimensions[0], data);
	}
	public BooleanMatrix(Map<String,Supplier<byte[]>> bundle) {
		this(JsonArrayInfo.parse(bundle.get(".json").get()), bundle.get(".dat").get());
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public boolean get(int x, int y) {
		return array[offset(x, y)];
	}
	public boolean get(IntPoint at) {
		return get(at.x, at.y);
	}
	public boolean get(int x, int y, boolean fallback) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return fallback;
		return array[offset(x, y)];
	}
	public boolean get(IntPoint at, boolean fallback) {
		return get(at.x, at.y, fallback);
	}
	public void set(int x, int y, boolean value) {
		array[offset(x, y)] = value;
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
