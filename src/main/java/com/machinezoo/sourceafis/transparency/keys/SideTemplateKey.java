// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public interface SideTemplateKey extends SerializedObjectKey<PersistentTemplate>, SideKey<PersistentTemplate> {
	@Override
	default String stem() {
		return "template";
	}
	@Override
	default Class<PersistentTemplate> type() {
		return PersistentTemplate.class;
	}
	static SideTemplateKey of(MatchSide side) {
		if (side == null)
			return new InputTemplateKey();
		return switch (side) {
			case PROBE -> new ProbeTemplateKey();
			case CANDIDATE -> new CandidateTemplateKey();
		};
	}
}
