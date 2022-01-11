// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.context;

public record ProbeImageKey() implements ContextImageKey {
	@Override
	public String name() {
		return "probe-image";
	}
}
