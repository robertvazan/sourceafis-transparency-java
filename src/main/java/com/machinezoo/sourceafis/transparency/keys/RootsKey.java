// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

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
}
