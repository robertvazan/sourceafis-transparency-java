// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

public record OutputScoreKey() implements TransparencyObjectKey<Double>, TransparencyContextKey<Double> {
	@Override
	public String name() {
		return "output-score";
	}
	@Override
	public Class<Double> type() {
		return Double.class;
	}
}
