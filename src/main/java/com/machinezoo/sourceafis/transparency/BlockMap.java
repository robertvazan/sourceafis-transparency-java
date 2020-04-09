// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class BlockMap {
	public IntPoint pixels;
	public BlockGrid primary;
	public BlockGrid secondary;
	public static BlockMap parse(Map<String, Supplier<byte[]>> bundle) {
		return TransparencyArchive.parse(bundle.get(".cbor").get(), BlockMap.class);
	}
}
