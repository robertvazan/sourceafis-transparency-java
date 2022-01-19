// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;

public record ByteMatrix(int width, int height, byte[] array) implements Serializable {
	public ByteMatrix {
		Validate.isTrue(width > 0 && height > 0, "Matrix dimensions must be positive.");
		Objects.requireNonNull(array, "Matrix array must be non-null.");
		Validate.isTrue(array.length == width * height, "Array length does not match matrix dimensions.");
	}
}
