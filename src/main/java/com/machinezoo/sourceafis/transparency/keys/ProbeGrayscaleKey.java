// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record ProbeGrayscaleKey() implements SideGrayscaleKey {
	@Override
	public MatchSide side() {
		return MatchSide.PROBE;
	}
}
