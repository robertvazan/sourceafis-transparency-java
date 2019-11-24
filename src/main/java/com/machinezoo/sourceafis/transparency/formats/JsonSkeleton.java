// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.formats;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;
import com.machinezoo.sourceafis.transparency.*;

public class JsonSkeleton {
	public int width;
	public int height;
	public List<IntPoint> minutiae;
	public List<JsonSkeletonRidge> ridges;
	public static JsonSkeleton parse(Map<String, Supplier<byte[]>> bundle) {
		return new Gson().fromJson(new String(bundle.get(".json").get(), StandardCharsets.UTF_8), JsonSkeleton.class);
	}
}
