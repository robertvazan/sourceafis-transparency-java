// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class MatchPairing {
	public MinutiaPair root;
	public List<PairingEdge> tree = new ArrayList<>();
	public List<PairingEdge> support = new ArrayList<>();
	public static MatchPairing parse(byte[] data) {
		return TransparencyArchive.parse(data, MatchPairing.class);
	}
}
