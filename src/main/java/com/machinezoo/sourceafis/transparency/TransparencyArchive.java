// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import com.machinezoo.sourceafis.transparency.keys.*;

public interface TransparencyArchive {
	List<TransparencyKey<?>> keys();
	int count(TransparencyKey<?> key);
	Optional<String> mime(TransparencyKey<?> key, int offset);
	Optional<byte[]> read(TransparencyKey<?> key, int offset);
	default <T> Optional<T> deserialize(TransparencyKey<T> key, int offset) {
		if (offset < 0 || offset >= count(key))
			return Optional.empty();
		return Optional.of(key.deserialize(mime(key, offset).get(), read(key, offset).get()));
	}
	default Optional<String> mime(TransparencyKey<?> key) {
		return mime(key, 0);
	}
	default Optional<byte[]> read(TransparencyKey<?> key) {
		return read(key, 0);
	}
	default <T> Optional<T> deserialize(TransparencyKey<T> key) {
		return deserialize(key, 0);
	}
}
