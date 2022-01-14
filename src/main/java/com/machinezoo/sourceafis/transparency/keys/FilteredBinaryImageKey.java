// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record FilteredBinaryImageKey() implements SerializedObjectKey<BooleanMatrix> {
	@Override
	public String name() {
		return "filtered-binary-image";
	}
	@Override
	public Class<BooleanMatrix> type() {
		return BooleanMatrix.class;
	}
}
