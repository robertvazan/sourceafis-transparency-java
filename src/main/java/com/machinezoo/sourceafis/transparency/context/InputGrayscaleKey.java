// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.context;

public record InputGrayscaleKey() implements ContextGrayscaleKey {
	@Override
	public String name() {
		return "input-grayscale";
	}
}
