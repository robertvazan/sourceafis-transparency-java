// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;

public interface EdgeShape extends Serializable {
	int length();
	@Angle double referenceAngle();
	@Angle double neighborAngle();
}
