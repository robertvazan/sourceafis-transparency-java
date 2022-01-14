// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

public record InputImageKey() implements ContextImageKey {
	@Override
	public String name() {
		return "input-image";
	}
}
