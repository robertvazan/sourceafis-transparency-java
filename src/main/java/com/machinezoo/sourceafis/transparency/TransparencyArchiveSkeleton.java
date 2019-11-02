// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class TransparencyArchiveSkeleton {
	private final TransparencyArchive archive;
	private final String prefix;
	TransparencyArchiveSkeleton(TransparencyArchive archive, String prefix) {
		this.archive = archive;
		this.prefix = prefix;
	}
	public BooleanMatrix binarized() {
		return TransparencyArchive.decode(archive.bundle(prefix + "binarized-skeleton"), BooleanMatrix::new);
	}
	public BooleanMatrix thinned() {
		return TransparencyArchive.decode(archive.bundle(prefix + "thinned-skeleton"), BooleanMatrix::new);
	}
	public SkeletonGraph traced() {
		return TransparencyArchive.decode(archive.bundle(prefix + "traced-skeleton"), SkeletonGraph::new);
	}
	public SkeletonGraph removedDots() {
		return TransparencyArchive.decode(archive.bundle(prefix + "removed-dots"), SkeletonGraph::new);
	}
	public SkeletonGraph removedPores() {
		return TransparencyArchive.decode(archive.bundle(prefix + "removed-pores"), SkeletonGraph::new);
	}
	public SkeletonGraph removedGaps() {
		return TransparencyArchive.decode(archive.bundle(prefix + "removed-gaps"), SkeletonGraph::new);
	}
	public SkeletonGraph removedTails() {
		return TransparencyArchive.decode(archive.bundle(prefix + "removed-tails"), SkeletonGraph::new);
	}
	public SkeletonGraph removedFragments() {
		return TransparencyArchive.decode(archive.bundle(prefix + "removed-fragments"), SkeletonGraph::new);
	}
}
