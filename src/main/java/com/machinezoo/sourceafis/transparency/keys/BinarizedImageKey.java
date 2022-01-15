// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record BinarizedImageKey() implements SerializedObjectKey<BooleanMatrix> {
	@Override
	public String stem() {
		return "binarized-image";
	}
	@Override
	public Class<BooleanMatrix> type() {
		return BooleanMatrix.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.EXTRACT_FEATURES);
	}
}
