// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record InnerMinutiaeKey() implements TransparencyObjectKey<Template> {
	@Override
	public String name() {
		return "inner-minutiae";
	}
	@Override
	public Class<Template> type() {
		return Template.class;
	}
}