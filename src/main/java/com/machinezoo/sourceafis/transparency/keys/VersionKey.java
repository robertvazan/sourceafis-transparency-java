// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.nio.charset.*;
import java.util.*;
import com.machinezoo.sourceafis.transparency.*;

public record VersionKey() implements TransparencyKey<String> {
	@Override
	public String stem() {
		return "version";
	}
	@Override
	public Class<String> type() {
		return String.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.values());
	}
	@Override
	public String mime() {
		return "text/plain";
	}
	@Override
	public String deserialize(String mime, byte[] data) {
		return switch (mime) {
			case "text/plain" -> new String(data, StandardCharsets.UTF_8);
			default -> throw new IllegalArgumentException();
		};
	}
	@Override
	public byte[] serialize(String mime, String object) {
		return switch (mime) {
			case "text/plain" -> object.getBytes(StandardCharsets.UTF_8);
			default -> throw new IllegalArgumentException();
		};
	}
}
