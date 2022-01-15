// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record ProbeTemplateKey() implements SerializedObjectKey<PersistentTemplate>, ContextKey<PersistentTemplate> {
	@Override
	public String stem() {
		return "probe-template";
	}
	@Override
	public Class<PersistentTemplate> type() {
		return PersistentTemplate.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
}
