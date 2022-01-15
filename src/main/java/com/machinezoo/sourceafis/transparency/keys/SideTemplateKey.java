// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public interface SideTemplateKey extends SerializedObjectKey<PersistentTemplate>, ContextKey<PersistentTemplate> {
	MatchSide side();
	@Override
	default String stem() {
		return side().toString().toLowerCase() + "-template";
	}
	@Override
	default Class<PersistentTemplate> type() {
		return PersistentTemplate.class;
	}
	@Override
	default List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
	static SideTemplateKey of(MatchSide side) {
		return switch (side) {
			case PROBE -> new ProbeTemplateKey();
			case CANDIDATE -> new CandidateTemplateKey();
		};
	}
}
