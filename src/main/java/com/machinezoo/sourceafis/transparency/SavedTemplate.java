// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import com.google.gson.*;

public class SavedTemplate {
	public final IntPoint size;
	public final TemplateMinutia[] minutiae;
	public SavedTemplate(JsonTemplate zip) {
		size = new IntPoint(zip.width, zip.height);
		this.minutiae = zip.minutiae.stream().map(TemplateMinutia::new).toArray(n -> new TemplateMinutia[n]);
	}
	public SavedTemplate(String json) {
		this(new Gson().fromJson(json, JsonTemplate.class));
	}
	public SavedTemplate(byte[] serialized) {
		this(JsonTemplate.parse(serialized));
	}
}
