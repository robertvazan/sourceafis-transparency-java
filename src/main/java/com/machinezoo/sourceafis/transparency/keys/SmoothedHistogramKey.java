// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record SmoothedHistogramKey() implements SerializedObjectKey<HistogramCube> {
	@Override
	public String stem() {
		return "smoothed-histogram";
	}
	@Override
	public Class<HistogramCube> type() {
		return HistogramCube.class;
	}
}
