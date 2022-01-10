// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.nio.charset.*;

public record VersionKey() implements TransparencyKey<String> {
	@Override
	public String name() {
		return "version";
	}
	@Override
	public String deserialize(String mime, byte[] data) {
		return switch (mime) {
			case "text/plain" -> new String(data, StandardCharsets.UTF_8);
			default -> throw new IllegalArgumentException();
		};
	}
}
