// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class TransparencyArchiveSkeleton {
	private final TransparencyArchive archive;
	private final String prefix;
	TransparencyArchiveSkeleton(TransparencyArchive archive, String prefix) {
		this.archive = archive;
		this.prefix = prefix;
	}
	public BooleanMap binarized() {
		return new BooleanMap(archive.bundle(prefix + "binarized-skeleton"));
	}
	public BooleanMap thinned() {
		return new BooleanMap(archive.bundle(prefix + "thinned-skeleton"));
	}
	public SkeletonGraph traced() {
		return new SkeletonGraph(archive.bundle(prefix + "traced-skeleton"));
	}
	public SkeletonGraph removedDots() {
		return new SkeletonGraph(archive.bundle(prefix + "removed-dots"));
	}
	public SkeletonGraph removedPores() {
		return new SkeletonGraph(archive.bundle(prefix + "removed-pores"));
	}
	public SkeletonGraph removedGaps() {
		return new SkeletonGraph(archive.bundle(prefix + "removed-gaps"));
	}
	public SkeletonGraph removedTails() {
		return new SkeletonGraph(archive.bundle(prefix + "removed-tails"));
	}
	public SkeletonGraph removedFragments() {
		return new SkeletonGraph(archive.bundle(prefix + "removed-fragments"));
	}
}
