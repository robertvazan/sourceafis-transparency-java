// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record PairingKey() implements SerializedObjectKey<PairingGraph> {
	@Override
	public String stem() {
		return "pairing";
	}
	@Override
	public Class<PairingGraph> type() {
		return PairingGraph.class;
	}
}
