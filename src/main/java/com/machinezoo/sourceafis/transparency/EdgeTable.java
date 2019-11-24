// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class EdgeTable {
	public NeighborEdge[][] edges;
	public static EdgeTable parse(Map<String, Supplier<byte[]>> bundle) {
		EdgeTable table = new EdgeTable();
		table.edges = new Gson().fromJson(new String(bundle.get(".json").get(), StandardCharsets.UTF_8), NeighborEdge[][].class);
		return table;
	}
}
