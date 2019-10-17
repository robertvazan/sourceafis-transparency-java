// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.formats;

import java.nio.charset.*;
import com.google.gson.*;

public class JsonArrayInfo {
	public String[] axes;
	public int[] dimensions;
	public String scalar;
	public int bitness;
	public String endianness;
	public String format;
	public static JsonArrayInfo parse(String json) {
		return new Gson().fromJson(json, JsonArrayInfo.class);
	}
	public static JsonArrayInfo parse(byte[] buffer) {
		return parse(new String(buffer, StandardCharsets.UTF_8));
	}
}
