// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;

public record UnknownTransparencyKey(String name) implements TransparencyKey<byte[]> {
	@Override
	public String stem() {
		return name;
	}
	@Override
	public Class<byte[]> type() {
		return byte[].class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.values());
	}
	@Override
	public String mime() {
		return "*/*";
	}
	@Override
	public byte[] deserialize(String mime, byte[] data) {
		return data;
	}
	@Override
	public byte[] serialize(String mime, byte[] object) {
		return object;
	}
}
