// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;

public interface EdgeShape extends Serializable {
	short length();
	@Angle float referenceAngle();
	@Angle float neighborAngle();
}
