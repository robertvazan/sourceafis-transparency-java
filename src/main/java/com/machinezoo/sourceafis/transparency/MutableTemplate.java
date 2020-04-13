// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class MutableTemplate {
	public IntPoint size = new IntPoint();
	/*
	 * Use array instead of a list, because we will need to index into it often.
	 */
	public MutableMinutia[] minutiae = new MutableMinutia[0];
	public static MutableTemplate parse(byte[] data) {
		return TransparencyArchive.parse(data, MutableTemplate.class);
	}
}
