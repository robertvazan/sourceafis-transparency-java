// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public interface TransparencyFilter {
	boolean accepts(TransparencyKey<?> key);
	default boolean accepts(String key) {
		return accepts(TransparencyKey.parse(key));
	}
	static TransparencyFilter any() {
		return new PredicateTransparencyFilter(k -> true);
	}
	static TransparencyFilter only(Predicate<TransparencyKey<?>> predicate) {
		return new PredicateTransparencyFilter(predicate);
	}
	static TransparencyFilter except(Predicate<TransparencyKey<?>> predicate) {
		return new PredicateTransparencyFilter(predicate.negate());
	}
	static TransparencyFilter only(Collection<TransparencyKey<?>> keys) {
		var set = new HashSet<>(keys);
		return only(set::contains);
	}
	static TransparencyFilter except(Collection<TransparencyKey<?>> keys) {
		var set = new HashSet<>(keys);
		return except(set::contains);
	}
	static TransparencyFilter only(TransparencyKey<?>... keys) {
		return only(List.of(keys));
	}
	static TransparencyFilter except(TransparencyKey<?>... keys) {
		return except(List.of(keys));
	}
}
