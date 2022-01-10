// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.types.*;

public record SmoothedHistogramKey() implements TransparencyObjectKey<HistogramCube> {
	@Override
	public String name() {
		return "smoothed-histogram";
	}
	@Override
	public Class<HistogramCube> type() {
		return HistogramCube.class;
	}
}
