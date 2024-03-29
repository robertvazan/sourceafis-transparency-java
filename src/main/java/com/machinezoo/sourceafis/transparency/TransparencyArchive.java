// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public interface TransparencyArchive {
	/*
	 * Canonical order corresponding to the usual logging order. Unknown keys are at the end, sorted alphabetically.
	 */
	List<TransparencyKey<?>> keys();
	<T> List<TransparencyRecord<T>> enumerate(TransparencyKey<T> key);
	/*
	 * Same order as keys(). Canonical serialization format.
	 */
	default List<TransparencyRecord<?>> toList() {
		var dump = new ArrayList<TransparencyRecord<?>>();
		for (var key : keys())
			dump.addAll(enumerate(key));
		return dump;
	}
	static TransparencyArchive from(Collection<TransparencyRecord<?>> collection) {
		return new TransparencyBuffer()
			.append(collection)
			.toArchive();
	}
	default <T> Optional<TransparencyRecord<T>> get(TransparencyKey<T> key, int offset) {
		var list = enumerate(key);
		if (offset < 0 || offset >= list.size())
			return Optional.empty();
		return Optional.of(list.get(offset));
	}
	default <T> Optional<TransparencyRecord<T>> get(TransparencyKey<T> key) {
		return get(key, 0);
	}
	default int count(TransparencyKey<?> key) {
		return enumerate(key).size();
	}
	default boolean contains(TransparencyKey<?> key) {
		return !enumerate(key).isEmpty();
	}
	default Optional<byte[]> read(TransparencyKey<?> key, int offset) {
		return get(key, offset).map(r -> r.data());
	}
	default Optional<byte[]> read(TransparencyKey<?> key) {
		return read(key, 0);
	}
	default <T> Optional<T> deserialize(TransparencyKey<T> key, int offset) {
		return get(key, offset).map(r -> r.deserialize());
	}
	default <T> Optional<T> deserialize(TransparencyKey<T> key) {
		return deserialize(key, 0);
	}
}
