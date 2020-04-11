// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class Template {
	public IntPoint size;
	public TemplateMinutia[] minutiae;
	private static Template parse(RawTemplate raw) {
		Template template = new Template();
		template.size = new IntPoint(raw.width, raw.height);
		template.minutiae = raw.minutiae.stream()
			.map(m -> {
				TemplateMinutia minutia = new TemplateMinutia();
				minutia.x = m.x;
				minutia.y = m.y;
				minutia.direction = m.direction;
				minutia.type = m.type.equals("ending") ? MinutiaType.ENDING : MinutiaType.BIFURCATION;
				return minutia;
			})
			.toArray(n -> new TemplateMinutia[n]);
		return template;
	}
	public static Template parseIO(byte[] serialized) {
		return parse(RawTemplate.parseIO(serialized));
	}
	public static Template parse(byte[] data) {
		return parse(RawTemplate.parse(data));
	}
}
