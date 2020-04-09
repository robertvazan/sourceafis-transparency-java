// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class EdgeTable {
	public NeighborEdge[][] edges;
	public static EdgeTable parse(Map<String, Supplier<byte[]>> bundle) {
		EdgeTable table = new EdgeTable();
		table.edges = TransparencyArchive.parse(bundle.get(".cbor").get(), NeighborEdge[][].class);
		return table;
	}
}
