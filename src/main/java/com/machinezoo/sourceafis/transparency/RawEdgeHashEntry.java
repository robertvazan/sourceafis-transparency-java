package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class RawEdgeHashEntry {
	public int key;
	public List<IndexedEdge> edges = new ArrayList<>();
	public static List<RawEdgeHashEntry> parseAll(byte[] data) {
		return Arrays.asList(TransparencyArchive.parse(data, RawEdgeHashEntry[].class));
	}
}
