// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

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
}
