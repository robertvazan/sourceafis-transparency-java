// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import org.apache.commons.lang3.*;

public interface TransparencyKey<T> {
	String name();
	T deserialize(String mime, byte[] data);
	byte[] serialize(String mime, T object);
	static List<TransparencyKey<?>> all() {
		return TransparencyKeys.ALL;
	}
	static TransparencyKey<?> parse(String name) {
		Validate.notBlank(name);
		var known = TransparencyKeys.BY_NAME.get(name);
		return known != null ? known : new UnknownTransparencyKey(name);
	}
}
