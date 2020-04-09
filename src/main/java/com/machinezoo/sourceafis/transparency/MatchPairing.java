// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class MatchPairing {
	public MinutiaPair root;
	public List<PairingEdge> tree = new ArrayList<>();
	public List<PairingEdge> support = new ArrayList<>();
	public static MatchPairing parse(Map<String, Supplier<byte[]>> bundle) {
		return TransparencyArchive.parse(bundle.get(".cbor").get(), MatchPairing.class);
	}
}
