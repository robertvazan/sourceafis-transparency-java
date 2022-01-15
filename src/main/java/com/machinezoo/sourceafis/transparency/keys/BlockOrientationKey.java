// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record BlockOrientationKey() implements SerializedObjectKey<DoublePointMatrix> {
	@Override
	public String stem() {
		return "block-orientation";
	}
	@Override
	public Class<DoublePointMatrix> type() {
		return DoublePointMatrix.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.EXTRACT_FEATURES);
	}
}
