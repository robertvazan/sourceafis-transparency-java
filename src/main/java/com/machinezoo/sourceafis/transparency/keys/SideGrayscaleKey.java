// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public interface SideGrayscaleKey extends SerializedObjectKey<ByteMatrix>, SideKey<ByteMatrix> {
	@Override
	default String stem() {
		return "grayscale";
	}
	@Override
	default Class<ByteMatrix> type() {
		return ByteMatrix.class;
	}
	static SideGrayscaleKey of(MatchSide side) {
		if (side == null)
			return new InputGrayscaleKey();
		return switch (side) {
			case PROBE -> new ProbeGrayscaleKey();
			case CANDIDATE -> new CandidateGrayscaleKey();
		};
	}
}
