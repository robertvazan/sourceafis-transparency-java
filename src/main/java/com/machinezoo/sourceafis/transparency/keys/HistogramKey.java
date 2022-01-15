// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record HistogramKey() implements SerializedObjectKey<HistogramCube> {
	@Override
	public String stem() {
		return "histogram";
	}
	@Override
	public Class<HistogramCube> type() {
		return HistogramCube.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.EXTRACT_FEATURES);
	}
}
