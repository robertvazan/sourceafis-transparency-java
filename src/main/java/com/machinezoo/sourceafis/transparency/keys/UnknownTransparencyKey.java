// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

public record UnknownTransparencyKey(String name) implements TransparencyKey<byte[]> {
	@Override
	public byte[] deserialize(String mime, byte[] data) {
		return data;
	}
}
