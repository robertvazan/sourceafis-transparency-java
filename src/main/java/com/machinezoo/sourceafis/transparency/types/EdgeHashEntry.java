// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.util.*;

public record EdgeHashEntry(int key, IndexedEdge[] edges) {
	public EdgeHashEntry {
		Objects.requireNonNull(edges, "Edge list must be non-null.");
		for (var edge : edges)
			Objects.requireNonNull(edge, "Every edge must be non-null.");
	}
}
