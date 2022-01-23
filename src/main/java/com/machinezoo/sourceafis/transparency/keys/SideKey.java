// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public interface SideKey<T> extends ContextKey<T> {
	/*
	 * Null means no side (InputX classes).
	 */
	MatchSide side();
	@Override
	default String name() {
		if (side() == null)
			return "input-" + stem();
		return side().toString().toLowerCase() + "-" + stem();
	}
	@Override
	default List<TransparentOperation> operations() {
		if (side() == null)
			return List.of(TransparentOperation.EXTRACT_FEATURES, TransparentOperation.PREPARE_PROBE, TransparentOperation.DESERIALIZE_TEMPLATE);
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
}
