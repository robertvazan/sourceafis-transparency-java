// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class IntPoint implements Iterable<IntPoint> {
	public int x;
	public int y;
	public IntPoint() {
	}
	public IntPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override public boolean equals(Object obj) {
		if (!(obj instanceof IntPoint))
			return false;
		IntPoint other = (IntPoint)obj;
		return x == other.x && y == other.y;
	}
	@Override public int hashCode() {
		return Objects.hash(x, y);
	}
	public int area() {
		return x * y;
	}
	public DoublePoint toDouble() {
		return new DoublePoint(x, y);
	}
	public DoublePoint center() {
		return new DoublePoint(x + 0.5, y + 0.5);
	}
	@Override public Iterator<IntPoint> iterator() {
		return new InteriorIterator();
	}
	private class InteriorIterator implements Iterator<IntPoint> {
		int atX;
		int atY;
		@Override public boolean hasNext() {
			return atY < y && atX < x;
		}
		@Override public IntPoint next() {
			if (!hasNext())
				throw new NoSuchElementException();
			IntPoint result = new IntPoint(atX, atY);
			++atX;
			if (atX >= x) {
				atX = 0;
				++atY;
			}
			return result;
		}
		@Override public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
