// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class Template {
	public IntPoint size = new IntPoint();
	/*
	 * Use array instead of a list, because we will need to index into it often.
	 */
	public TemplateMinutia[] minutiae = new TemplateMinutia[0];
	public static Template parse(byte[] data) {
		return TransparencyArchive.parse(data, Template.class);
	}
}
