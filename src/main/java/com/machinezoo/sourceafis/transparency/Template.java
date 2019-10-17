// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import com.google.gson.*;
import com.machinezoo.sourceafis.transparency.formats.*;

public class Template {
	public final IntPoint size;
	public final TemplateMinutia[] minutiae;
	public Template(JsonTemplate zip) {
		size = new IntPoint(zip.width, zip.height);
		this.minutiae = zip.minutiae.stream().map(TemplateMinutia::new).toArray(n -> new TemplateMinutia[n]);
	}
	public Template(String json) {
		this(new Gson().fromJson(json, JsonTemplate.class));
	}
	public Template(byte[] serialized) {
		this(JsonTemplate.parse(serialized));
	}
}
