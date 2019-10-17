// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class MatchPairing {
	public MinutiaPair root;
	public PairingEdge[] tree;
	public PairingEdge[] support;
	public static MatchPairing parse(String json) {
		return new Gson().fromJson(json, MatchPairing.class);
	}
	public static MatchPairing parse(byte[] buffer) {
		return parse(new String(buffer, StandardCharsets.UTF_8));
	}
	public static MatchPairing parse(Map<String,Supplier<byte[]>> bundle) {
		return parse(bundle.get(".json").get());
	}
}
