// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public record BlocksKey() implements SerializedObjectKey<BlockMap> {
	@Override
	public String stem() {
		return "blocks";
	}
	@Override
	public Class<BlockMap> type() {
		return BlockMap.class;
	}
	@Override
	public List<TransparentOperation> operations() {
		return List.of(TransparentOperation.EXTRACT_FEATURES);
	}
}
