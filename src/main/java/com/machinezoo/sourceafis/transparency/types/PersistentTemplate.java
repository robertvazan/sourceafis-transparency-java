// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.util.*;
import org.apache.commons.lang3.*;

public record PersistentTemplate(
	String version,
	int width,
	int height,
	int[] positionsX,
	int[] positionsY,
	@Angle double[] directions,
	String types) {
	public PersistentTemplate {
		Objects.requireNonNull(version, "Persistent template is missing version information.");
		Validate.isTrue(width >= 0 && height >= 0, "Template dimensions must be non-negative.");
		Objects.requireNonNull(positionsX, "X position array is null.");
		Objects.requireNonNull(positionsY, "Y position array is null.");
		Objects.requireNonNull(directions, "Direction array is null.");
		Objects.requireNonNull(types, "Type string is null.");
		Validate.isTrue(positionsX.length == types.length() && positionsY.length == types.length() && directions.length == types.length(), "Inconsistent array lengths.");
		for (double angle : directions)
			DoubleAngles.validate(angle);
		for (int i = 0; i < types.length(); ++i)
			Validate.isTrue(types.charAt(i) == 'E' || types.charAt(i) == 'B', "Minutia type must be ending or bifurcation.");
	}
	public IntPoint size() {
		return new IntPoint(width, height);
	}
	public MinutiaPoint[] minutiae() {
		var minutiae = new MinutiaPoint[types.length()];
		for (int i = 0; i < types.length(); ++i) {
			minutiae[i] = new MinutiaPoint(
				new IntPoint(positionsX[i], positionsY[i]),
				directions[i],
				types.charAt(i) == 'B' ? MinutiaType.BIFURCATION : MinutiaType.ENDING);
		}
		return minutiae;
	}
	public Template unpack() {
		return new Template(size(), minutiae());
	}
}
