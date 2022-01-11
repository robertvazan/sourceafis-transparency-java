// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import com.machinezoo.sourceafis.transparency.keys.*;
import com.machinezoo.stagean.*;

/*
 * Must be constructed with immutable lists. Map can be mutable.
 */
@DraftCode("Nicer toString().")
record PlainTransparencyArchive(Map<TransparencyKey<?>, List<TransparencyRecord<?>>> map) implements TransparencyArchive {
	@Override
	public List<TransparencyKey<?>> keys() {
		var unsorted = new HashSet<>(map.keySet());
		var sorted = new ArrayList<TransparencyKey<?>>();
		for (var key : TransparencyKey.all()) {
			if (unsorted.contains(key)) {
				sorted.add(key);
				unsorted.remove(key);
			}
		}
		unsorted.stream()
			.sorted(Comparator.comparing(k -> k.name()))
			.forEach(sorted::add);
		return sorted;
	}
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<TransparencyRecord<T>> enumerate(TransparencyKey<T> key) {
		var list = map.get(key);
		if (list == null)
			return Collections.emptyList();
		return (List<TransparencyRecord<T>>)(List<?>)list;
	}
}
