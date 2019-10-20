package com.machinezoo.sourceafis.transparency.formats;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class JsonVersion {
	public String version;
	public static String parse(String json) {
		return new Gson().fromJson(json, JsonVersion.class).version;
	}
	public static String parse(byte[] buffer) {
		return parse(new String(buffer, StandardCharsets.UTF_8));
	}
	public static String parse(Map<String, Supplier<byte[]>> bundle) {
		return parse(bundle.get(".json").get());
	}
}
