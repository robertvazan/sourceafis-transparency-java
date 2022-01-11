// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.context;

public record OutputTemplateKey() implements ContextTemplateKey {
	@Override
	public String name() {
		return "output-template";
	}
}
