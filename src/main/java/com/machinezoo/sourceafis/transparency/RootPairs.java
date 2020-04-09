// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class RootPairs {
	public List<MinutiaPair> pairs = new ArrayList<>();
	public static RootPairs parse(Map<String, Supplier<byte[]>> bundle) {
		RootPairs roots = new RootPairs();
		roots.pairs = Arrays.asList(TransparencyArchive.parse(bundle.get(".cbor").get(), MinutiaPair[].class));
		return roots;
	}
}
