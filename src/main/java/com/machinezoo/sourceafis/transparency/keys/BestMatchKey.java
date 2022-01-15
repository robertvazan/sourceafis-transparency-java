// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.nio.charset.*;
import java.util.*;
import com.machinezoo.sourceafis.transparency.*;

public record BestMatchKey() implements TransparencyKey<Integer> {
	@Override
	public String stem() {
		return "best-match";
	}
	@Override
	public Class<Integer> type() {
		return Integer.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.COMPARE_CANDIDATE);
	}
	@Override
	public String mime() {
		return "text/plain";
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
