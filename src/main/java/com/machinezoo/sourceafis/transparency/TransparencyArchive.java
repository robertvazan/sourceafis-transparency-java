// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;
import com.machinezoo.sourceafis.transparency.formats.*;

public abstract class TransparencyArchive {
	public abstract List<String> enumerate();
	public abstract byte[] read(String path);
	private String path(String numberless, int offset) {
		return enumerate().stream()
			.sorted()
			.map(TransparencyPath::new)
			.filter(p -> numberless.equals(p.typed() + p.suffix()))
			.skip(offset)
			.map(TransparencyPath::filename)
			.findFirst()
			.orElse(null);
	}
	private Map<String, Supplier<byte[]>> bundle(String keyword, int offset) {
		String json = path(keyword + ".json", offset);
		String data = path(keyword + ".dat", offset);
		if (json == null && data == null)
			return null;
		Map<String, Supplier<byte[]>> map = new HashMap<>();
		if (json != null)
			map.put(".json", () -> read(json));
		if (data != null)
			map.put(".dat", () -> read(data));
		return map;
	}
	private static <T> T parse(Map<String, Supplier<byte[]>> bundle, Function<Map<String, Supplier<byte[]>>, T> parser) {
		if (bundle == null)
			return null;
		return parser.apply(bundle);
	}
	private <T> T parse(String keyword, Function<Map<String, Supplier<byte[]>>, T> parser) {
		return parse(bundle(keyword, 0), parser);
	}
	public String version() {
		return Optional.ofNullable(parse("version", JsonVersion::parse)).map(v -> v.version).orElse(null);
	}
	public DoubleMatrix decoded() {
		return parse("decoded-image", DoubleMatrix::parse);
	}
	public DoubleMatrix scaled() {
		return parse("scaled-image", DoubleMatrix::parse);
	}
	public BlockMap blocks() {
		return parse("block-map", BlockMap::parse);
	}
	public HistogramCube histogram() {
		return parse("histogram", HistogramCube::parse);
	}
	public HistogramCube smoothedHistogram() {
		return parse("smoothed-histogram", HistogramCube::parse);
	}
	public DoubleMatrix contrast() {
		return parse("clipped-contrast", DoubleMatrix::parse);
	}
	public BooleanMatrix absoluteMask() {
		return parse("absolute-contrast-mask", BooleanMatrix::parse);
	}
	public BooleanMatrix relativeMask() {
		return parse("relative-contrast-mask", BooleanMatrix::parse);
	}
	public BooleanMatrix combinedMask() {
		return parse("combined-mask", BooleanMatrix::parse);
	}
	public BooleanMatrix filteredMask() {
		return parse("filtered-mask", BooleanMatrix::parse);
	}
	public DoubleMatrix equalized() {
		return parse("equalized-image", DoubleMatrix::parse);
	}
	public DoublePointMatrix pixelwiseOrientation() {
		return parse("pixelwise-orientation", DoublePointMatrix::parse);
	}
	public DoublePointMatrix blockOrientation() {
		return parse("block-orientation", DoublePointMatrix::parse);
	}
	public DoublePointMatrix smoothedOrientation() {
		return parse("smoothed-orientation", DoublePointMatrix::parse);
	}
	public DoubleMatrix parallelSmoothing() {
		return parse("parallel-smoothing", DoubleMatrix::parse);
	}
	public DoubleMatrix orthogonalSmoothing() {
		return parse("orthogonal-smoothing", DoubleMatrix::parse);
	}
	public BooleanMatrix binarized() {
		return parse("binarized-image", BooleanMatrix::parse);
	}
	public BooleanMatrix filteredBinary() {
		return parse("filtered-binary-image", BooleanMatrix::parse);
	}
	public BooleanMatrix pixelMask() {
		return parse("pixel-mask", BooleanMatrix::parse);
	}
	public BooleanMatrix innerMask() {
		return parse("inner-mask", BooleanMatrix::parse);
	}
	private <T> T pickSkeleton(Function<SkeletonType, T> decoder) {
		T ridges = decoder.apply(SkeletonType.RIDGES);
		if (ridges != null)
			return ridges;
		return decoder.apply(SkeletonType.VALLEYS);
	}
	public BooleanMatrix binarizedSkeleton(SkeletonType skeleton) {
		return parse(skeleton.prefix() + "-binarized-skeleton", BooleanMatrix::parse);
	}
	public BooleanMatrix binarizedSkeleton() {
		return pickSkeleton(this::binarizedSkeleton);
	}
	public BooleanMatrix thinned(SkeletonType skeleton) {
		return parse(skeleton.prefix() + "-thinned-skeleton", BooleanMatrix::parse);
	}
	public BooleanMatrix thinned() {
		return pickSkeleton(this::thinned);
	}
	public SkeletonGraph traced(SkeletonType skeleton) {
		return parse(skeleton.prefix() + "-traced-skeleton", SkeletonGraph::parse);
	}
	public SkeletonGraph traced() {
		return pickSkeleton(this::traced);
	}
	public SkeletonGraph removedDots(SkeletonType skeleton) {
		return parse(skeleton.prefix() + "-removed-dots", SkeletonGraph::parse);
	}
	public SkeletonGraph removedDots() {
		return pickSkeleton(this::removedDots);
	}
	public SkeletonGraph removedPores(SkeletonType skeleton) {
		return parse(skeleton.prefix() + "-removed-pores", SkeletonGraph::parse);
	}
	public SkeletonGraph removedPores() {
		return pickSkeleton(this::removedPores);
	}
	public SkeletonGraph removedGaps(SkeletonType skeleton) {
		return parse(skeleton.prefix() + "-removed-gaps", SkeletonGraph::parse);
	}
	public SkeletonGraph removedGaps() {
		return pickSkeleton(this::removedGaps);
	}
	public SkeletonGraph removedTails(SkeletonType skeleton) {
		return parse(skeleton.prefix() + "-removed-tails", SkeletonGraph::parse);
	}
	public SkeletonGraph removedTails() {
		return pickSkeleton(this::removedTails);
	}
	public SkeletonGraph removedFragments(SkeletonType skeleton) {
		return parse(skeleton.prefix() + "-removed-fragments", SkeletonGraph::parse);
	}
	public SkeletonGraph removedFragments() {
		return pickSkeleton(this::removedFragments);
	}
	public Template skeletonMinutiae() {
		return parse("skeleton-minutiae", Template::parse);
	}
	public Template innerMinutiae() {
		return parse("inner-minutiae", Template::parse);
	}
	public Template removedMinutiaClouds() {
		return parse("removed-minutia-clouds", Template::parse);
	}
	public Template topMinutiae() {
		return parse("top-minutiae", Template::parse);
	}
	public Template shuffledMinutiae() {
		return parse("shuffled-minutiae", Template::parse);
	}
	public EdgeTable edgeTable() {
		return parse("edge-table", EdgeTable::parse);
	}
	public EdgeHash edgeHash() {
		return parse("edge-hash", EdgeHash::parse);
	}
	public RootPairs rootPairs() {
		return parse("root-pairs", RootPairs::parse);
	}
	public int pairingCount() {
		return (int)enumerate().stream()
			.filter(n -> new TransparencyPath(n).keyword().equals("pairing"))
			.count();
	}
	public MatchPairing pairing(int offset) {
		return parse(bundle("pairing", offset), MatchPairing::parse);
	}
	public MatchPairing pairing() {
		return pairing(bestMatch().orElse(0));
	}
	public MatchScoring score(int offset) {
		return parse(bundle("score", offset), MatchScoring::parse);
	}
	public MatchScoring score() {
		return score(bestMatch().orElse(0));
	}
	public OptionalInt bestMatch() {
		JsonBestMatch best = parse("best-match", JsonBestMatch::parse);
		return best != null ? OptionalInt.of(best.offset) : OptionalInt.empty();
	}
}
