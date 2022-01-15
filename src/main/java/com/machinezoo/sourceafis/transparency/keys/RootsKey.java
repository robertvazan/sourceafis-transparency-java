// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record RootsKey() implements SerializedObjectKey<MinutiaPair[]> {
	@Override
	public String stem() {
		return "roots";
	}
	@Override
	public Class<MinutiaPair[]> type() {
		return MinutiaPair[].class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
}
