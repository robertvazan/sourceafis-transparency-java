// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record InputTemplateKey() implements SideTemplateKey {
	@Override
	public MatchSide side() {
		return null;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.PREPARE_PROBE, TransparentOperation.DESERIALIZE_TEMPLATE);
	}
}
