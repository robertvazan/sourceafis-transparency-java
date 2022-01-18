// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record InputImageKey() implements SideImageKey {
	@Override
	public MatchSide side() {
		return null;
	}
}
