// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import org.apache.commons.lang3.*;

public interface TransparencyKey<T> {
	String name();
	/*
	 * MIME type. In case support for multiple data encodings is added in the future, this will be the preferred MIME type.
	 * Wildcards are required for keys that do not fully understand their content,
	 * e.g. image/* for encoded image keys and * / * for unknown keys.
	 */
	String mime();
	/*
	 * Supplied MIME must be specific enough to allow unambiguous decoding of the data.
	 * That usually means fully specified MIME (e.g. application/cbor).
	 * 
	 * In case of trivial deserialization into byte[] (encoded images, unknown keys),
	 * MIME type identifies source format, not destination format.
	 * Deserialization therefore never performs conversion (e.g. image/png to image/jpeg).
	 * Since trivial deserialization does not perform any decoding, any MIME type can be specified.
	 * Wildcard (e.g. image/* or * / *) is recommended for clarity.
	 */
	T deserialize(String mime, byte[] data);
	/*
	 * MIME type is optional. Callers can specify partial wildcard (image/*) or full wildcard (* / *).
	 * In case of trivial serialization into byte[] (encoded images, unknown keys),
	 * MIME type identifies destination format, not source format,
	 * but since trivial serialization does not change the content, MIME type must match source format too.
	 * That means wildcards are required for keys that do not know format of the data they are holding,
	 * e.g. image/* for encoded image keys and * / * for unknown keys.
	 */
	byte[] serialize(String mime, T object);
	/*
	 * In case support for multiple data encodings is added in the future,
	 * this will work only if the data has key's preferred MIME type.
	 */
	default T deserialize(byte[] data) {
		return deserialize(mime(), data);
	}
	default byte[] serialize(T object) {
		return serialize(mime(), object);
	}
	static List<TransparencyKey<?>> all() {
		return TransparencyKeys.ALL;
	}
	static TransparencyKey<?> parse(String name) {
		Validate.notBlank(name);
		var known = TransparencyKeys.BY_NAME.get(name);
		return known != null ? known : new UnknownTransparencyKey(name);
	}
}