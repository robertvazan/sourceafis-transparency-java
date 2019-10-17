// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.formats;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class JsonBestMatch {
	public int offset;
	public static int parse(String json) {
		return new Gson().fromJson(json, JsonBestMatch.class).offset;
	}
	public static int parse(byte[] buffer) {
		return parse(new String(buffer, StandardCharsets.UTF_8));
	}
	public static int parse(Map<String, Supplier<byte[]>> bundle) {
		return parse(bundle.get(".json").get());
	}
}
