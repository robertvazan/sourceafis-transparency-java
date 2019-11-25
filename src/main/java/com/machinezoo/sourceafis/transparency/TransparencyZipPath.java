// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.regex.*;

public class TransparencyZipPath {
	private final String filename;
	public String filename() {
		return filename;
	}
	private final TransparencyPath parsed;
	public TransparencyPath parsed() {
		return parsed;
	}
	private TransparencyZipPath(String filename, TransparencyPath parsed) {
		this.filename = filename;
		this.parsed = parsed;
	}
	private static final Pattern filenameRe = Pattern.compile("^[0-9]+-([a-z-]+)(.[a-z]+)$");
	public static TransparencyZipPath parse(String filename) {
		Matcher matcher = filenameRe.matcher(filename);
		if (!matcher.matches())
			return null;
		TransparencyPath parsed = new TransparencyPath(matcher.group(1), matcher.group(2));
		return new TransparencyZipPath(filename, parsed);
	}
	@Override public boolean equals(Object obj) {
		return obj instanceof TransparencyZipPath && Objects.equals(filename, ((TransparencyZipPath)obj).filename);
	}
	@Override public int hashCode() {
		return filename.hashCode();
	}
}
