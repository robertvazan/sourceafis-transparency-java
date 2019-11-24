// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

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
	public static MinutiaPair[] parse(Map<String, Supplier<byte[]>> bundle) {
		return new Gson().fromJson(new String(bundle.get(".json").get(), StandardCharsets.UTF_8), MinutiaPair[].class);
	}
}
