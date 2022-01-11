// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.context;

import com.machinezoo.sourceafis.transparency.keys.*;
import com.machinezoo.sourceafis.transparency.types.*;

public interface ContextTemplateKey extends TransparencyObjectKey<PersistentTemplate>, TransparencyContextKey<PersistentTemplate> {
	@Override
	default Class<PersistentTemplate> type() {
		return PersistentTemplate.class;
	}
}
