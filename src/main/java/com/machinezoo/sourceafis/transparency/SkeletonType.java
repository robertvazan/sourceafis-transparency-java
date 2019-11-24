// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public enum SkeletonType {
	RIDGES,
	VALLEYS;
	String prefix() {
		return name().toLowerCase();
	}
}
