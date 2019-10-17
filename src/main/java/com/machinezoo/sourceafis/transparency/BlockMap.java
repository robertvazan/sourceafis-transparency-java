// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class BlockMap {
	public IntPoint pixels;
	public BlockGrid primary;
	public BlockGrid secondary;
	public static BlockMap parse(String json) {
		return new Gson().fromJson(json, BlockMap.class);
	}
	public static BlockMap parse(byte[] buffer) {
		return parse(TransparencyUtils.text(buffer));
	}
	public static BlockMap parse(Map<String,Supplier<byte[]>> bundle) {
		return parse(bundle.get(".json").get());
	}
}
