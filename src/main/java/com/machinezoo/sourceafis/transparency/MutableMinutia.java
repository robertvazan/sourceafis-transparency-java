// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class MutableMinutia {
	public IntPoint position = new IntPoint();
	public double direction;
	public MinutiaType type;
	public DoublePoint center() {
		return position.center();
	}
}
