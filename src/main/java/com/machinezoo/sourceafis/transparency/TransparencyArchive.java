// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.cbor.*;
import com.machinezoo.noexception.*;
import com.machinezoo.sourceafis.transparency.types.*;

public abstract class TransparencyArchive {
	public abstract List<TransparencyPath> paths();
	public abstract int count(TransparencyPath path);
	public abstract String mime(TransparencyPath path);
	public abstract byte[] read(TransparencyPath path, int offset);
	private static final ObjectMapper mapper = new ObjectMapper(new CBORFactory());
	private static <T> T parse(byte[] data, Class<T> type) {
		if (data == null)
			return null;
		return Exceptions.wrap(IllegalArgumentException::new).get(() -> mapper.readValue(data, type));
	}
	private <T> T parse(String key, int offset, Class<T> type) {
		return parse(read(new TransparencyPath(key), offset), type);
	}
	private <T> T parse(String key, Class<T> type) {
		return parse(key, 0, type);
	}
	private <T> T parse(SkeletonType skeleton, String keyword, Class<T> type) {
		return parse(read(new TransparencyPath(skeleton, keyword), 0), type);
	}
	public String version() {
		byte[] data = read(new TransparencyPath("version"), 0);
		if (data == null)
			return null;
		return new String(data, StandardCharsets.UTF_8);
	}
	public DoubleMatrix decoded() {
		return parse("decoded-image", DoubleMatrix.class);
	}
	public DoubleMatrix scaled() {
		return parse("scaled-image", DoubleMatrix.class);
	}
	public BlockMap blocks() {
		return parse("blocks", BlockMap.class);
	}
	public HistogramCube histogram() {
		return parse("histogram", HistogramCube.class);
	}
	public HistogramCube smoothedHistogram() {
		return parse("smoothed-histogram", HistogramCube.class);
	}
	public DoubleMatrix contrast() {
		return parse("contrast", DoubleMatrix.class);
	}
	public BooleanMatrix absoluteMask() {
		return parse("absolute-contrast-mask", BooleanMatrix.class);
	}
	public BooleanMatrix relativeMask() {
		return parse("relative-contrast-mask", BooleanMatrix.class);
	}
	public BooleanMatrix combinedMask() {
		return parse("combined-mask", BooleanMatrix.class);
	}
	public BooleanMatrix filteredMask() {
		return parse("filtered-mask", BooleanMatrix.class);
	}
	public DoubleMatrix equalized() {
		return parse("equalized-image", DoubleMatrix.class);
	}
	public DoublePointMatrix pixelwiseOrientation() {
		return parse("pixelwise-orientation", DoublePointMatrix.class);
	}
	public DoublePointMatrix blockOrientation() {
		return parse("block-orientation", DoublePointMatrix.class);
	}
	public DoublePointMatrix smoothedOrientation() {
		return parse("smoothed-orientation", DoublePointMatrix.class);
	}
	public DoubleMatrix parallel() {
		return parse("parallel-smoothing", DoubleMatrix.class);
	}
	public DoubleMatrix orthogonal() {
		return parse("orthogonal-smoothing", DoubleMatrix.class);
	}
	public BooleanMatrix binarized() {
		return parse("binarized-image", BooleanMatrix.class);
	}
	public BooleanMatrix filteredBinary() {
		return parse("filtered-binary-image", BooleanMatrix.class);
	}
	public BooleanMatrix pixelMask() {
		return parse("pixel-mask", BooleanMatrix.class);
	}
	public BooleanMatrix innerMask() {
		return parse("inner-mask", BooleanMatrix.class);
	}
	private <T> T pickSkeleton(Function<SkeletonType, T> decoder) {
		T ridges = decoder.apply(SkeletonType.RIDGES);
		if (ridges != null)
			return ridges;
		return decoder.apply(SkeletonType.VALLEYS);
	}
	public BooleanMatrix binarizedSkeleton(SkeletonType skeleton) {
		return parse(skeleton, "binarized-skeleton", BooleanMatrix.class);
	}
	public BooleanMatrix binarizedSkeleton() {
		return pickSkeleton(this::binarizedSkeleton);
	}
	public BooleanMatrix thinned(SkeletonType skeleton) {
		return parse(skeleton, "thinned-skeleton", BooleanMatrix.class);
	}
	public BooleanMatrix thinned() {
		return pickSkeleton(this::thinned);
	}
	public SkeletonGraph traced(SkeletonType skeleton) {
		return parse(skeleton, "traced-skeleton", SkeletonGraph.class);
	}
	public SkeletonGraph traced() {
		return pickSkeleton(this::traced);
	}
	public SkeletonGraph dots(SkeletonType skeleton) {
		return parse(skeleton, "removed-dots", SkeletonGraph.class);
	}
	public SkeletonGraph dots() {
		return pickSkeleton(this::dots);
	}
	public SkeletonGraph pores(SkeletonType skeleton) {
		return parse(skeleton, "removed-pores", SkeletonGraph.class);
	}
	public SkeletonGraph pores() {
		return pickSkeleton(this::pores);
	}
	public SkeletonGraph gaps(SkeletonType skeleton) {
		return parse(skeleton, "removed-gaps", SkeletonGraph.class);
	}
	public SkeletonGraph gaps() {
		return pickSkeleton(this::gaps);
	}
	public SkeletonGraph tails(SkeletonType skeleton) {
		return parse(skeleton, "removed-tails", SkeletonGraph.class);
	}
	public SkeletonGraph tails() {
		return pickSkeleton(this::tails);
	}
	public SkeletonGraph fragments(SkeletonType skeleton) {
		return parse(skeleton, "removed-fragments", SkeletonGraph.class);
	}
	public SkeletonGraph fragments() {
		return pickSkeleton(this::fragments);
	}
	public Template skeletonMinutiae() {
		return parse("skeleton-minutiae", Template.class);
	}
	public Template innerMinutiae() {
		return parse("inner-minutiae", Template.class);
	}
	public Template clouds() {
		return parse("removed-minutia-clouds", Template.class);
	}
	public Template topMinutiae() {
		return parse("top-minutiae", Template.class);
	}
	public Template shuffled() {
		return parse("shuffled-minutiae", Template.class);
	}
	public NeighborEdge[][] edges() {
		return parse("edge-table", NeighborEdge[][].class);
	}
	public EdgeHashEntry[] hash() {
		return parse("edge-hash", EdgeHashEntry[].class);
	}
	public MinutiaPair[] roots() {
		return parse("roots", MinutiaPair[].class);
	}
	public int pairings() {
		return count(new TransparencyPath("pairing"));
	}
	public PairingGraph pairing(int offset) {
		return parse("pairing", offset, PairingGraph.class);
	}
	public PairingGraph pairing() {
		return pairing(best().orElse(0));
	}
	public ScoreBreakdown score(int offset) {
		return parse("score", offset, ScoreBreakdown.class);
	}
	public ScoreBreakdown score() {
		return score(best().orElse(0));
	}
	public OptionalInt best() {
		byte[] data = read(new TransparencyPath("best-match"), 0);
		if (data == null)
			return OptionalInt.empty();
		int best = Integer.parseInt(new String(data, StandardCharsets.UTF_8));
		return best >= 0 ? OptionalInt.of(best) : OptionalInt.empty();
	}
}
