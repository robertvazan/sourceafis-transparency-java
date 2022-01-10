// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record EdgeTableKey() implements TransparencyObjectKey<NeighborEdge[][]> {
	@Override
	public String name() {
		return "edge-table";
	}
	@Override
	public Class<NeighborEdge[][]> type() {
		return NeighborEdge[][].class;
	}
}
