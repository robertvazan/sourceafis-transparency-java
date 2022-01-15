// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public interface SideImageKey extends EncodedImageKey, ContextKey<byte[]> {
	MatchSide side();
	@Override
	default String stem() {
		return side().toString().toLowerCase() + "-image";
	}
	@Override
	default List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
	static SideImageKey of(MatchSide side) {
		return switch (side) {
			case PROBE -> new ProbeImageKey();
			case CANDIDATE -> new CandidateImageKey();
		};
	}
}
