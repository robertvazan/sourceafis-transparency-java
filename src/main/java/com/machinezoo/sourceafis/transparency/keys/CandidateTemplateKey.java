// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

public record CandidateTemplateKey() implements ContextTemplateKey {
	@Override
	public String name() {
		return "candidate-template";
	}
}
