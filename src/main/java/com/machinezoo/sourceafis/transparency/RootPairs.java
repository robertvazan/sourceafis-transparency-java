// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public class RootPairs {
	public List<MinutiaPair> pairs = new ArrayList<>();
	public static RootPairs parse(byte[] data) {
		RootPairs roots = new RootPairs();
		roots.pairs = Arrays.asList(TransparencyArchive.parse(data, MinutiaPair[].class));
		return roots;
	}
}
