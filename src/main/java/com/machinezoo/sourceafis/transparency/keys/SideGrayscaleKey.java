// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public interface SideGrayscaleKey extends SerializedObjectKey<GrayscaleImage>, ContextKey<GrayscaleImage> {
	MatchSide side();
	@Override
	default String stem() {
		return side().toString().toLowerCase() + "-grayscale";
	}
	@Override
	default Class<GrayscaleImage> type() {
		return GrayscaleImage.class;
	}
	@Override
	default List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
	static SideGrayscaleKey of(MatchSide side) {
		return switch (side) {
			case PROBE -> new ProbeGrayscaleKey();
			case CANDIDATE -> new CandidateGrayscaleKey();
		};
	}
}
