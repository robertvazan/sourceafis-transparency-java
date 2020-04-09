// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class BestMatch {
	public int offset;
	public static BestMatch parse(Map<String, Supplier<byte[]>> bundle) {
		return TransparencyArchive.parse(bundle.get(".cbor").get(), BestMatch.class);
	}
}
