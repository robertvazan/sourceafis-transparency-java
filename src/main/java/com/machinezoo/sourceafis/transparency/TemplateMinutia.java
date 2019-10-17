// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class TemplateMinutia {
	public final int x;
	public final int y;
	public final double direction;
	public final MinutiaType type;
	public TemplateMinutia(JsonMinutia zip) {
		x = zip.x;
		y = zip.y;
		direction = zip.direction;
		type = zip.type.equals("ending") ? MinutiaType.ENDING : MinutiaType.BIFURCATION;
	}
	public IntPoint position() {
		return new IntPoint(x, y);
	}
	public DoublePoint center() {
		return position().center();
	}
}
