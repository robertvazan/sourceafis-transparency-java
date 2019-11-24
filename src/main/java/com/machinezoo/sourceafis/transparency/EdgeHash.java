// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.util.*;
import java.util.function.*;
import gnu.trove.map.hash.*;
import gnu.trove.set.hash.*;

public class EdgeHash {
	public final TIntObjectHashMap<IndexedEdge[]> hash = new TIntObjectHashMap<>();
	public final List<IndexedEdge> edges = new ArrayList<>();
	public static EdgeHash parse(Map<String, Supplier<byte[]>> bundle) {
		ByteBuffer buffer = ByteBuffer.wrap(bundle.get(".dat").get());
		EdgeHash eh = new EdgeHash();
		TIntHashSet seen = new TIntHashSet();
		int keyCount = buffer.getInt();
		for (int i = 0; i < keyCount; ++i) {
			int key = buffer.getInt();
			int valueCount = buffer.getInt();
			IndexedEdge[] list = new IndexedEdge[valueCount];
			for (int j = 0; j < valueCount; ++j) {
				IndexedEdge edge = new IndexedEdge(buffer);
				list[j] = edge;
				int id = (edge.reference << 16) + edge.neighbor;
				if (!seen.contains(id)) {
					seen.add(id);
					eh.edges.add(edge);
				}
			}
			eh.hash.put(key, list);
		}
		return eh;
	}
}
