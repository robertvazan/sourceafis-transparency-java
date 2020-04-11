// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class EdgeTable {
	public NeighborEdge[][] edges;
	public static EdgeTable parse(byte[] data) {
		EdgeTable table = new EdgeTable();
		table.edges = TransparencyArchive.parse(data, NeighborEdge[][].class);
		return table;
	}
}
