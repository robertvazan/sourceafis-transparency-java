// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import org.apache.commons.lang3.*;

public record EdgePair(int probeFrom, int probeTo, int candidateFrom, int candidateTo) {
	public EdgePair {
		Validate.isTrue(probeFrom >= 0 && probeTo >= 0 && candidateFrom >= 0 && candidateTo >= 0, "Minutia offsets must be non-negative.");
		Validate.isTrue(probeFrom != probeTo && candidateFrom != candidateTo, "Edge cannot connect minutia with itself.");
	}
	public MinutiaPair from() {
		return new MinutiaPair(probeFrom, candidateFrom);
	}
	public MinutiaPair to() {
		return new MinutiaPair(probeTo, candidateTo);
	}
}
