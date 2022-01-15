// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record InnerMinutiaeKey() implements SerializedObjectKey<Template> {
	@Override
	public String stem() {
		return "inner-minutiae";
	}
	@Override
	public Class<Template> type() {
		return Template.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.EXTRACT_FEATURES);
	}
}
