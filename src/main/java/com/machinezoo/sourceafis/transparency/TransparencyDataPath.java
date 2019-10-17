package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.regex.*;

public class TransparencyDataPath {
	private static final Pattern filenameRe = Pattern.compile("^[0-9]+-(ridges-|valleys-|)([a-z-]+)(.[a-z]+)$");
	public static Set<String> keywords() {
		return keywords;
	}
	private static final Set<String> keywords = new HashSet<>(Arrays.asList(
		"decoded-image",
		"scaled-image",
		"block-map",
		"histogram",
		"smoothed-histogram",
		"clipped-contrast",
		"absolute-contrast-mask",
		"relative-contrast-mask",
		"combined-mask",
		"filtered-mask",
		"equalized-image",
		"pixelwise-orientation",
		"block-orientation",
		"smoothed-orientation",
		"parallel-smoothing",
		"orthogonal-smoothing",
		"binarized-image",
		"filtered-binary-image",
		"pixel-mask",
		"inner-mask",
		"binarized-skeleton",
		"thinned-skeleton",
		"traced-skeleton",
		"removed-dots",
		"removed-pores",
		"removed-gaps",
		"removed-tails",
		"removed-fragments",
		"skeleton-minutiae",
		"inner-minutiae",
		"removed-minutia-clouds",
		"top-minutiae",
		"shuffled-minutiae",
		"edge-table",
		"edge-hash",
		"root-pairs",
		"pairing",
		"score",
		"best-match"));
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
	public TransparencyDataPath(String filename) {
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
