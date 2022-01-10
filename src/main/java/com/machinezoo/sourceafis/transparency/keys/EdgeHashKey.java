// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record EdgeHashKey() implements TransparencyObjectKey<EdgeHashEntry[]> {
	@Override
	public String name() {
		return "edge-hash";
	}
	@Override
	public Class<EdgeHashEntry[]> type() {
		return EdgeHashEntry[].class;
	}
}
