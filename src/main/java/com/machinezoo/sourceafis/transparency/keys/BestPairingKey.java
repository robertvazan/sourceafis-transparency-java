// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record BestPairingKey() implements SerializedObjectKey<PairingGraph> {
	@Override
	public String stem() {
		return "pairing";
	}
	@Override
	public String name() {
		return "best-pairing";
	}
	@Override
	public Class<PairingGraph> type() {
		return PairingGraph.class;
	}
}
