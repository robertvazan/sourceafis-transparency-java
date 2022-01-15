// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;

public enum TransparentOperation {
	EXTRACT_FEATURES,
	PREPARE_PROBE,
	COMPARE_CANDIDATE,
	DESERIALIZE_TEMPLATE;
	public List<TransparencyKey<?>> keys() {
		return TransparencyKey.all().stream()
			.filter(k -> k.operations().contains(this))
			.toList();
	}
}
