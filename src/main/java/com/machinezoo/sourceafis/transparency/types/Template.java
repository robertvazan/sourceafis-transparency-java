// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;
import com.machinezoo.stagean.*;

public record Template(IntPoint size, MinutiaPoint[] minutiae) implements Serializable {
	@DraftCode("Implement full validation.")
	public Template {
		Objects.requireNonNull(size, "Template has null size.");
		Validate.isTrue(size.x() > 0 && size.y() > 0, "Template dimensions must be positive.");
		Objects.requireNonNull(minutiae, "Template has null minutia list.");
		for (var minutia : minutiae)
			Objects.requireNonNull(minutia, "Template contains null minutia.");
	}
}
