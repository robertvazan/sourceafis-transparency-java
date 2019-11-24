// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class MatchPairing {
	public MinutiaPair root;
	public List<PairingEdge> tree = new ArrayList<>();
	public List<PairingEdge> support = new ArrayList<>();
	public static MatchPairing parse(Map<String, Supplier<byte[]>> bundle) {
		return new Gson().fromJson(new String(bundle.get(".json").get(), StandardCharsets.UTF_8), MatchPairing.class);
	}
}
