// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class RootPairs {
	public MinutiaPair[] pairs;
	public static RootPairs parse(Map<String, Supplier<byte[]>> bundle) {
		RootPairs roots = new RootPairs();
		roots.pairs = new Gson().fromJson(new String(bundle.get(".json").get(), StandardCharsets.UTF_8), MinutiaPair[].class);
		return roots;
	}
}