// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import it.unimi.dsi.fastutil.ints.*;

public class EdgeHash {
	public Int2ObjectMap<List<IndexedEdge>> table = new Int2ObjectOpenHashMap<>();
	public static EdgeHash parse(Map<String, Supplier<byte[]>> bundle) {
		EdgeHash eh = new EdgeHash();
		for (RawEdgeHashEntry entry : RawEdgeHashEntry.parseAll(bundle))
			eh.table.put(entry.key, entry.edges);
		return eh;
	}
	public Stream<IndexedEdge> edges() {
		IntSet seen = new IntOpenHashSet();
		return table.values().stream()
			.flatMap(List::stream)
			.filter(e -> {
				int id = (e.reference << 16) + e.neighbor;
				boolean unique = !seen.contains(id);
				if (unique)
					seen.add(id);
				return unique;
			});
	}
}
