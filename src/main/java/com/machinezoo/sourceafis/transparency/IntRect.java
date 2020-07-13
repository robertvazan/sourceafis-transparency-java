// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.stream.*;

public class IntRect implements Iterable<IntPoint> {
	public int x;
	public int y;
	public int width;
	public int height;
	public IntRect() {
	}
	public IntRect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public IntRect(IntPoint size) {
		this(0, 0, size.x, size.y);
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
	public DoublePoint center() {
		return new DoublePoint(x + width / 2.0, y + height / 2.0);
	}
	public double radius() {
		return 0.5 * Math.min(width, height);
	}
	public static IntRect between(int startX, int startY, int endX, int endY) {
		return new IntRect(startX, startY, endX - startX, endY - startY);
	}
	public static IntRect between(IntPoint start, IntPoint end) {
		return between(start.x, start.y, end.x, end.y);
	}
	@Override
	public Iterator<IntPoint> iterator() {
		return new InteriorIterator();
	}
	private class InteriorIterator implements Iterator<IntPoint> {
		int atX;
		int atY;
		@Override
		public boolean hasNext() {
			return atY < height && atX < width;
		}
		@Override
		public IntPoint next() {
			if (!hasNext())
				throw new NoSuchElementException();
			IntPoint result = new IntPoint(x + atX, y + atY);
			++atX;
			if (atX >= width) {
				atX = 0;
				++atY;
			}
			return result;
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	public Stream<IntPoint> stream() {
		return IntStream.range(y, y + height)
			.boxed()
			.flatMap(sy -> IntStream.range(x, x + width)
				.mapToObj(sx -> new IntPoint(sx, sy)));
	}
}
