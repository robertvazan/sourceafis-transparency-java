// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;
import com.machinezoo.sourceafis.transparency.formats.*;

public class Template {
	public IntPoint size;
	public TemplateMinutia[] minutiae;
	private static Template parse(JsonTemplate json) {
		Template template = new Template();
		template.size = new IntPoint(json.width, json.height);
		template.minutiae = json.minutiae.stream()
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
	public static Template parse(byte[] serialized) {
		return parse(JsonTemplate.parse(serialized));
	}
	public static Template parse(Map<String, Supplier<byte[]>> bundle) {
		return parse(JsonTemplate.parse(bundle));
	}
}
