// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record EdgeTableKey() implements SerializedObjectKey<NeighborEdge[][]> {
	@Override
	public String stem() {
		return "edge-table";
	}
	@Override
	public Class<NeighborEdge[][]> type() {
		return NeighborEdge[][].class;
	}
}
