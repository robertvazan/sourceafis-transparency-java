// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.formats;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class JsonArrayInfo {
	public String[] axes;
	public int[] dimensions;
	public String scalar;
	public int bitness;
	public String endianness;
	public String format;
	public static JsonArrayInfo parse(Map<String, Supplier<byte[]>> bundle) {
		return new Gson().fromJson(new String(bundle.get(".json").get(), StandardCharsets.UTF_8), JsonArrayInfo.class);
	}
}
