package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class RawEdgeHashEntry {
	public int key;
	public List<IndexedEdge> edges = new ArrayList<>();
	public static List<RawEdgeHashEntry> parseAll(Map<String, Supplier<byte[]>> bundle) {
		return Arrays.asList(TransparencyArchive.parse(bundle.get(".cbor").get(), RawEdgeHashEntry[].class));
	}
}
