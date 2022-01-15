// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
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
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.EXTRACT_FEATURES, TransparentOperation.DESERIALIZE_TEMPLATE);
	}
}
