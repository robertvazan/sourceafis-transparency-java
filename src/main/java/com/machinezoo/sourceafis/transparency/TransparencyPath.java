// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.regex.*;

public class TransparencyPath {
	private static final Pattern filenameRe = Pattern.compile("^[0-9]+-(ridges-|valleys-|)([a-z-]+)(.[a-z]+)$");
	private final String filename;
	public String filename() {
		return filename;
	}
	private final SkeletonType skeletonType;
	public SkeletonType skeletonType() {
		return skeletonType;
	}
	private final String keyword;
	public String keyword() {
		return keyword;
	}
	private final String typed;
	public String typed() {
		return typed;
	}
	private final String suffix;
	public String suffix() {
		return suffix;
	}
	public TransparencyPath(String filename) {
		this.filename = filename;
		Matcher matcher = filenameRe.matcher(filename);
		if (matcher.find()) {
			switch (matcher.group(1)) {
			case "ridges-":
				skeletonType = SkeletonType.RIDGES;
				break;
			case "valleys-":
				skeletonType = SkeletonType.VALLEYS;
				break;
			default:
				skeletonType = null;
				break;
			}
			keyword = matcher.group(2);
			typed = matcher.group(1) + keyword;
			suffix = matcher.group(3);
		} else {
			skeletonType = null;
			keyword = filename;
			typed = filename;
			suffix = "";
		}
	}
}
