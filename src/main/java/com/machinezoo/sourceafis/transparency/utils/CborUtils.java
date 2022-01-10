// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.utils;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.cbor.*;
import com.machinezoo.noexception.*;

public class CborUtils {
	private static final ObjectMapper mapper = new ObjectMapper(new CBORFactory());
	public static <T> T deserialize(byte[] data, Class<T> clazz) {
		return Exceptions.wrap(IllegalArgumentException::new).get(() -> mapper.readValue(data, clazz));
	}
}
