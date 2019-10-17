package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class JsonBestMatch {
	public int offset;
	public static int parse(String json) {
		return new Gson().fromJson(json, JsonBestMatch.class).offset;
	}
	public static int parse(byte[] buffer) {
		return parse(TransparencyUtils.text(buffer));
	}
	public static int parse(Map<String,Supplier<byte[]>> bundle) {
		return parse(bundle.get(".json").get());
	}
}
