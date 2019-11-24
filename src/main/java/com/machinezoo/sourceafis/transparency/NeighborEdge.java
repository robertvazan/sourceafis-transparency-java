// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class NeighborEdge extends EdgeShape {
	public int neighbor;
	public static NeighborEdge[][] parse(Map<String, Supplier<byte[]>> bundle) {
		return new Gson().fromJson(new String(bundle.get(".json").get(), StandardCharsets.UTF_8), NeighborEdge[][].class);
	}
}
