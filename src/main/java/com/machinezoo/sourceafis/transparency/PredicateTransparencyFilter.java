// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

record PredicateTransparencyFilter(Predicate<TransparencyKey<?>> predicate) implements TransparencyFilter {
	@Override
	public boolean accepts(TransparencyKey<?> key) {
		Objects.requireNonNull(key);
		return predicate.test(key);
	}
}
