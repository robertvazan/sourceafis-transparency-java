// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;
import com.machinezoo.sourceafis.transparency.keys.*;
import com.machinezoo.stagean.*;

@ApiIssue("Add validation method, which is useful when type is an array.")
public interface TransparencyKey<T> extends Serializable {
	/*
	 * Represents a group of keys that are variants of each other.
	 * For example, skeleton keys have stem that omits the "ridges-" or "valleys-" prefix.
	 * Stem does not necessarily correspond to key class.
	 */
	String stem();
	default String name() {
		return stem();
	}
	Class<T> type();
	/*
	 * In order of preference. If it is not known whether operation applies, it should be included in the list.
	 */
	List<TransparentOperation> operations();
	/*
	 * Preferred operation to run to obtain data for this key.
	 */
	default TransparentOperation operation() {
		return operations().get(0);
	}
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
	@DraftApi
	/*
	 * Custom transparency keys can be defined by applications and it mostly works except this one method.
	 * Custom keys will not be recognized when parsing string keys.
	 * This could be addressed with ServiceLoader, but that seems to be an overkill at the moment.
	 */
	@ApiIssue("Cannot parse custom keys.")
	@ApiIssue("When multiple SourceAFIS versions are supported, key parsing will have to be more complicated.")
	static TransparencyKey<?> parse(String name) {
		Validate.notBlank(name);
		var known = TransparencyKeys.BY_NAME.get(name);
		return known != null ? known : new UnknownTransparencyKey(name);
	}
}
