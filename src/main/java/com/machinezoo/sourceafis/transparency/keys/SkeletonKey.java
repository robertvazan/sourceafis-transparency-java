// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import java.util.*;
import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.sourceafis.transparency.types.*;

public interface SkeletonKey<T> extends SerializedObjectKey<T> {
	SkeletonType skeleton();
	@Override
	default String name() {
		return skeleton().name().toLowerCase() + "-" + stem();
	}
	@Override
	default List<TransparentOperation> operations() {
		return List.of(TransparentOperation.EXTRACT_FEATURES);
	}
}
