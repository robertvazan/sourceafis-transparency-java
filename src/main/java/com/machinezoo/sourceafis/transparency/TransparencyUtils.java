// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import com.google.gson.*;

class TransparencyUtils {
	public static String text(byte[] buffer) {
		return new String(buffer, StandardCharsets.UTF_8);
	}
	public static <T> T json(byte[] buffer, Class<T> clazz) {
		return new Gson().fromJson(text(buffer), clazz);
	}
}
