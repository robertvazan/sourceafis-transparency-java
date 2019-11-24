// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class TemplateMinutia {
	public int x;
	public int y;
	public double direction;
	public MinutiaType type;
	public IntPoint position() {
		return new IntPoint(x, y);
	}
	public DoublePoint center() {
		return position().center();
	}
}
