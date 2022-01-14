// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record InputTemplateKey() implements SerializedObjectKey<PersistentTemplate>, ContextKey<PersistentTemplate> {
	@Override
	public String stem() {
		return "input-template";
	}
	@Override
	public Class<PersistentTemplate> type() {
		return PersistentTemplate.class;
	}
}