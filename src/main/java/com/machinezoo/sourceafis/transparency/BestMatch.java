// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class BestMatch {
	public int offset;
	public static BestMatch parse(byte[] data) {
		return TransparencyArchive.parse(data, BestMatch.class);
	}
}
