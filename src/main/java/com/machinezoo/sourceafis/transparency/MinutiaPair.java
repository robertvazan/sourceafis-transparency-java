// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class MinutiaPair {
	public int probe;
	public int candidate;
	public MinutiaPair() {
	}
	public MinutiaPair(int probe, int candidate) {
		this.probe = probe;
		this.candidate = candidate;
	}
	public int side(MatchSide side) {
		switch (side) {
		case PROBE:
			return probe;
		case CANDIDATE:
			return candidate;
		default:
			throw new IllegalArgumentException();
		}
	}
}
