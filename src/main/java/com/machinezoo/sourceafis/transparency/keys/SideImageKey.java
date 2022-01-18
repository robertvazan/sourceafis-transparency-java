// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public interface SideImageKey extends EncodedImageKey, SideKey<byte[]> {
	@Override
	default String stem() {
		return "image";
	}
	static SideImageKey of(MatchSide side) {
		if (side == null)
			return new InputImageKey();
		return switch (side) {
			case PROBE -> new ProbeImageKey();
			case CANDIDATE -> new CandidateImageKey();
		};
	}
}
