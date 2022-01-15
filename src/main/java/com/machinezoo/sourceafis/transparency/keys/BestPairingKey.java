// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
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
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
}
