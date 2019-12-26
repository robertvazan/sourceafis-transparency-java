// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import it.unimi.dsi.fastutil.ints.*;

public class EdgeHash {
	public Int2ObjectMap<IndexedEdge[]> table = new Int2ObjectOpenHashMap<>();
	public static EdgeHash parse(Map<String, Supplier<byte[]>> bundle) {
		ByteBuffer buffer = ByteBuffer.wrap(bundle.get(".dat").get());
		EdgeHash eh = new EdgeHash();
		int keyCount = buffer.getInt();
		for (int i = 0; i < keyCount; ++i) {
			int key = buffer.getInt();
			int valueCount = buffer.getInt();
			IndexedEdge[] list = new IndexedEdge[valueCount];
			for (int j = 0; j < valueCount; ++j) {
				IndexedEdge edge = new IndexedEdge();
				edge.reference = buffer.getInt();
				edge.neighbor = buffer.getInt();
				edge.length = buffer.getInt();
				edge.referenceAngle = buffer.getDouble();
				edge.neighborAngle = buffer.getDouble();
				list[j] = edge;
			}
			eh.table.put(key, list);
		}
		return eh;
	}
	public Stream<IndexedEdge> edges() {
		IntSet seen = new IntOpenHashSet();
		return table.values().stream()
			.flatMap(Arrays::stream)
			.filter(e -> {
				int id = (e.reference << 16) + e.neighbor;
				boolean unique = !seen.contains(id);
				if (unique)
					seen.add(id);
				return unique;
			});
	}
}
