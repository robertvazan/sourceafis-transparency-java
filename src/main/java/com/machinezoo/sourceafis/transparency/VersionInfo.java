// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class VersionInfo {
	public String version;
	public static VersionInfo parse(byte[] data) {
		return TransparencyArchive.parse(data, VersionInfo.class);
	}
}
