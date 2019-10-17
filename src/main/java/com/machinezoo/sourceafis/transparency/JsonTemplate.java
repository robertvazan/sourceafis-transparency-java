// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.zip.*;
import org.apache.commons.io.*;
import com.google.gson.*;
import com.machinezoo.noexception.*;

public class JsonTemplate {
	public int width;
	public int height;
	public List<JsonMinutia> minutiae;
	public static JsonTemplate parse(byte[] serialized) {
		return Exceptions.sneak().get(() -> {
			try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(serialized))) {
				byte[] decompressed = IOUtils.toByteArray(gzip);
				String json = new String(decompressed, StandardCharsets.UTF_8);
				return new Gson().fromJson(json, JsonTemplate.class);
			}
		});
	}
}
