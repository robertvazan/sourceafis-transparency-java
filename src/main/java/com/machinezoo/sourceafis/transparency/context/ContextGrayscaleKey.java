// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.context;

import com.machinezoo.sourceafis.transparency.keys.*;
import com.machinezoo.sourceafis.transparency.types.*;

public interface ContextGrayscaleKey extends TransparencyObjectKey<GrayscaleImage>, TransparencyContextKey<GrayscaleImage> {
	@Override
	default Class<GrayscaleImage> type() {
		return GrayscaleImage.class;
	}
}
