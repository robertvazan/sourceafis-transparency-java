// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record ScaledImageKey() implements SerializedObjectKey<DoubleMatrix> {
	@Override
	public String stem() {
		return "scaled-image";
	}
	@Override
	public Class<DoubleMatrix> type() {
		return DoubleMatrix.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.EXTRACT_FEATURES);
	}
}
