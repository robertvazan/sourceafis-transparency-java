// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.nio.charset.*;

public record BestMatchKey() implements TransparencyKey<Integer> {
	@Override
	public String name() {
		return "best-match";
	}
	@Override
	public Integer deserialize(String mime, byte[] data) {
		return switch (mime) {
			case "text/plain" -> Integer.parseInt(new String(data, StandardCharsets.UTF_8));
			default -> throw new IllegalArgumentException();
		};
	}
	@Override
	public byte[] serialize(String mime, Integer object) {
		return switch (mime) {
			case "text/plain" -> Integer.toString(object).getBytes(StandardCharsets.UTF_8);
			default -> throw new IllegalArgumentException();
		};
	}
}
