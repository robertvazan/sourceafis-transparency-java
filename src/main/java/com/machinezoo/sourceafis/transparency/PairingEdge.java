// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class PairingEdge {
	public int probeFrom;
	public int probeTo;
	public int candidateFrom;
	public int candidateTo;
	public MinutiaPair from() {
		return new MinutiaPair(probeFrom, candidateFrom);
	}
	public MinutiaPair to() {
		return new MinutiaPair(probeTo, candidateTo);
	}
}
