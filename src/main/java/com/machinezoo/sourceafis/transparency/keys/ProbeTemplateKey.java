// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record ProbeTemplateKey() implements SerializedObjectKey<PersistentTemplate>, ContextKey<PersistentTemplate> {
	@Override
	public String name() {
		return "probe-template";
	}
	@Override
	public Class<PersistentTemplate> type() {
		return PersistentTemplate.class;
	}
}
