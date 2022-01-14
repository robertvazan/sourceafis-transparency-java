// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record CandidateTemplateKey() implements SerializedObjectKey<PersistentTemplate>, ContextKey<PersistentTemplate> {
	@Override
	public String stem() {
		return "candidate-template";
	}
	@Override
	public Class<PersistentTemplate> type() {
		return PersistentTemplate.class;
	}
}