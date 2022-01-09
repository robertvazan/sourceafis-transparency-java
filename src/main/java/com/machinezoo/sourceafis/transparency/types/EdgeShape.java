// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

public interface EdgeShape {
	int length();
	@Angle double referenceAngle();
	@Angle double neighborAngle();
}
