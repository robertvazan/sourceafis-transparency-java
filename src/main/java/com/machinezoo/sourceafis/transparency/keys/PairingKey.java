// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record PairingKey() implements TransparencyObjectKey<PairingGraph> {
	@Override
	public String name() {
		return "pairing";
	}
	@Override
	public Class<PairingGraph> type() {
		return PairingGraph.class;
	}
}