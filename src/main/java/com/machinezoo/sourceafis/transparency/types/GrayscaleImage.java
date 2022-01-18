// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;

public record GrayscaleImage(int width, int height, byte[] pixels) implements Serializable {
	public GrayscaleImage {
		Validate.isTrue(width > 0 && height > 0, "Image dimensions must be positive.");
		Objects.requireNonNull(pixels, "Pixel array must be non-null.");
		Validate.isTrue(pixels.length == width * height, "Pixel array length does not match image dimensions.");
	}
}
