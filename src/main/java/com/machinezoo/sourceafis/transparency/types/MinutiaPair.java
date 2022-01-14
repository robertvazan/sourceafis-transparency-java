// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import org.apache.commons.lang3.*;

public record MinutiaPair(int probe, int candidate) implements Serializable {
	public MinutiaPair {
		Validate.isTrue(probe >= 0 && candidate >= 0, "Probe and candidate minutia offsets must be non-negative.");
	}
}
