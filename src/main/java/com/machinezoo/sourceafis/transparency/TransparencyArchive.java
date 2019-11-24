// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;
import com.machinezoo.sourceafis.transparency.formats.*;

public abstract class TransparencyArchive {
	public abstract List<String> enumerate();
	public abstract byte[] read(String path);
	private String path(String numberless) {
		return path(numberless, 0);
	}
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
	Map<String, Supplier<byte[]>> bundle(String bare) {
		return bundle(bare, 0);
	}
	Map<String, Supplier<byte[]>> bundle(String bare, int offset) {
		String json = path(bare + ".json", offset);
		String data = path(bare + ".dat", offset);
		if (json == null || data == null)
			return null;
		Map<String, Supplier<byte[]>> map = new HashMap<>();
		map.put(".json", () -> read(json));
		map.put(".dat", () -> read(data));
		return map;
	}
	Map<String, Supplier<byte[]>> json(String bare) {
		return json(bare, 0);
	}
	Map<String, Supplier<byte[]>> json(String bare, int offset) {
		String path = path(bare + ".json", offset);
		if (path == null)
			return null;
		Map<String, Supplier<byte[]>> map = new HashMap<>();
		map.put(".json", () -> read(path));
		return map;
	}
	Map<String, Supplier<byte[]>> data(String bare) {
		String path = path(bare + ".dat");
		if (path == null)
			return null;
		Map<String, Supplier<byte[]>> map = new HashMap<>();
		map.put(".dat", () -> read(path));
		return map;
	}
	static <T, R> R decode(T serialized, Function<T, R> decoder) {
		if (serialized == null)
			return null;
		return decoder.apply(serialized);
	}
	public String version() {
		return Optional.ofNullable(decode(json("version"), JsonVersion::parse)).map(v -> v.version).orElse(null);
	}
	public DoubleMatrix decoded() {
		return decode(bundle("decoded-image"), DoubleMatrix::parse);
	}
	public DoubleMatrix scaled() {
		return decode(bundle("scaled-image"), DoubleMatrix::parse);
	}
	public BlockMap blocks() {
		return decode(json("block-map"), BlockMap::parse);
	}
	public HistogramCube histogram() {
		return decode(bundle("histogram"), HistogramCube::parse);
	}
	public HistogramCube smoothedHistogram() {
		return decode(bundle("smoothed-histogram"), HistogramCube::parse);
	}
	public DoubleMatrix contrast() {
		return decode(bundle("clipped-contrast"), DoubleMatrix::parse);
	}
	public BooleanMatrix absoluteMask() {
		return decode(bundle("absolute-contrast-mask"), BooleanMatrix::parse);
	}
	public BooleanMatrix relativeMask() {
		return decode(bundle("relative-contrast-mask"), BooleanMatrix::parse);
	}
	public BooleanMatrix combinedMask() {
		return decode(bundle("combined-mask"), BooleanMatrix::parse);
	}
	public BooleanMatrix filteredMask() {
		return decode(bundle("filtered-mask"), BooleanMatrix::parse);
	}
	public DoubleMatrix equalized() {
		return decode(bundle("equalized-image"), DoubleMatrix::parse);
	}
	public DoublePointMatrix pixelwiseOrientation() {
		return decode(bundle("pixelwise-orientation"), DoublePointMatrix::parse);
	}
	public DoublePointMatrix blockOrientation() {
		return decode(bundle("block-orientation"), DoublePointMatrix::parse);
	}
	public DoublePointMatrix smoothedOrientation() {
		return decode(bundle("smoothed-orientation"), DoublePointMatrix::parse);
	}
	public DoubleMatrix parallelSmoothing() {
		return decode(bundle("parallel-smoothing"), DoubleMatrix::parse);
	}
	public DoubleMatrix orthogonalSmoothing() {
		return decode(bundle("orthogonal-smoothing"), DoubleMatrix::parse);
	}
	public BooleanMatrix binarized() {
		return decode(bundle("binarized-image"), BooleanMatrix::parse);
	}
	public BooleanMatrix filteredBinary() {
		return decode(bundle("filtered-binary-image"), BooleanMatrix::parse);
	}
	public BooleanMatrix pixelMask() {
		return decode(bundle("pixel-mask"), BooleanMatrix::parse);
	}
	public BooleanMatrix innerMask() {
		return decode(bundle("inner-mask"), BooleanMatrix::parse);
	}
	private <T> T pickSkeleton(Function<SkeletonType, T> decoder) {
		T ridges = decoder.apply(SkeletonType.RIDGES);
		if (ridges != null)
			return ridges;
		return decoder.apply(SkeletonType.VALLEYS);
	}
	public BooleanMatrix binarizedSkeleton(SkeletonType skeleton) {
		return decode(bundle(skeleton.prefix() + "-binarized-skeleton"), BooleanMatrix::parse);
	}
	public BooleanMatrix binarizedSkeleton() {
		return pickSkeleton(this::binarizedSkeleton);
	}
	public BooleanMatrix thinned(SkeletonType skeleton) {
		return decode(bundle(skeleton.prefix() + "-thinned-skeleton"), BooleanMatrix::parse);
	}
	public BooleanMatrix thinned() {
		return pickSkeleton(this::thinned);
	}
	public SkeletonGraph traced(SkeletonType skeleton) {
		return decode(bundle(skeleton.prefix() + "-traced-skeleton"), SkeletonGraph::parse);
	}
	public SkeletonGraph traced() {
		return pickSkeleton(this::traced);
	}
	public SkeletonGraph removedDots(SkeletonType skeleton) {
		return decode(bundle(skeleton.prefix() + "-removed-dots"), SkeletonGraph::parse);
	}
	public SkeletonGraph removedDots() {
		return pickSkeleton(this::removedDots);
	}
	public SkeletonGraph removedPores(SkeletonType skeleton) {
		return decode(bundle(skeleton.prefix() + "-removed-pores"), SkeletonGraph::parse);
	}
	public SkeletonGraph removedPores() {
		return pickSkeleton(this::removedPores);
	}
	public SkeletonGraph removedGaps(SkeletonType skeleton) {
		return decode(bundle(skeleton.prefix() + "-removed-gaps"), SkeletonGraph::parse);
	}
	public SkeletonGraph removedGaps() {
		return pickSkeleton(this::removedGaps);
	}
	public SkeletonGraph removedTails(SkeletonType skeleton) {
		return decode(bundle(skeleton.prefix() + "-removed-tails"), SkeletonGraph::parse);
	}
	public SkeletonGraph removedTails() {
		return pickSkeleton(this::removedTails);
	}
	public SkeletonGraph removedFragments(SkeletonType skeleton) {
		return decode(bundle(skeleton.prefix() + "-removed-fragments"), SkeletonGraph::parse);
	}
	public SkeletonGraph removedFragments() {
		return pickSkeleton(this::removedFragments);
	}
	public Template skeletonMinutiae() {
		return decode(json("skeleton-minutiae"), Template::parse);
	}
	public Template innerMinutiae() {
		return decode(json("inner-minutiae"), Template::parse);
	}
	public Template removedMinutiaClouds() {
		return decode(json("removed-minutia-clouds"), Template::parse);
	}
	public Template topMinutiae() {
		return decode(json("top-minutiae"), Template::parse);
	}
	public Template shuffledMinutiae() {
		return decode(json("shuffled-minutiae"), Template::parse);
	}
	public NeighborEdge[][] edgeTable() {
		return decode(json("edge-table"), NeighborEdge::parse);
	}
	public EdgeHash edgeHash() {
		return decode(data("edge-hash"), EdgeHash::parse);
	}
	public MinutiaPair[] rootPairs() {
		return decode(json("root-pairs"), MinutiaPair::parse);
	}
	public int pairingCount() {
		return (int)enumerate().stream()
			.filter(n -> new TransparencyPath(n).keyword().equals("pairing"))
			.count();
	}
	public MatchPairing pairing(int offset) {
		return decode(json("pairing", offset), MatchPairing::parse);
	}
	public MatchPairing pairing() {
		return pairing(bestMatch().orElse(0));
	}
	public MatchScoring score(int offset) {
		return decode(json("score", offset), MatchScoring::parse);
	}
	public MatchScoring score() {
		return score(bestMatch().orElse(0));
	}
	public OptionalInt bestMatch() {
		Map<String, Supplier<byte[]>> bundle = json("best-match");
		return bundle != null ? OptionalInt.of(JsonBestMatch.parse(bundle)) : OptionalInt.empty();
	}
}
