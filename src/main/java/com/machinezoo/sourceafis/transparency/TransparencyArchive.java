// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.cbor.*;
import com.machinezoo.noexception.*;

public abstract class TransparencyArchive {
	public abstract List<TransparencyPath> paths();
	public abstract int count(TransparencyPath path);
	public abstract String mime(TransparencyPath path);
	public abstract byte[] read(TransparencyPath path, int offset);
	private static final ObjectMapper mapper = new ObjectMapper(new CBORFactory())
		.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	static <T> T parse(byte[] data, Class<T> type) {
		return Exceptions.wrap(IllegalArgumentException::new).get(() -> mapper.readValue(data, type));
	}
	private static <T> T parse(byte[] data, Function<byte[], T> parser) {
		if (data == null)
			return null;
		return parser.apply(data);
	}
	private <T> T parse(String key, int offset, Function<byte[], T> parser) {
		return parse(read(new TransparencyPath(key), offset), parser);
	}
	private <T> T parse(String key, Function<byte[], T> parser) {
		return parse(key, 0, parser);
	}
	private <T> T parse(SkeletonType skeleton, String keyword, Function<byte[], T> parser) {
		return parse(read(new TransparencyPath(skeleton, keyword), 0), parser);
	}
	public String version() {
		byte[] data = read(new TransparencyPath("version"), 0);
		if (data == null)
			return null;
		return new String(data, StandardCharsets.UTF_8);
	}
	public DoubleMatrix decoded() {
		return parse("decoded-image", DoubleMatrix::parse);
	}
	public DoubleMatrix scaled() {
		return parse("scaled-image", DoubleMatrix::parse);
	}
	public BlockMap blocks() {
		return parse("blocks", BlockMap::parse);
	}
	public HistogramCube histogram() {
		return parse("histogram", HistogramCube::parse);
	}
	public HistogramCube smoothedHistogram() {
		return parse("smoothed-histogram", HistogramCube::parse);
	}
	public DoubleMatrix contrast() {
		return parse("contrast", DoubleMatrix::parse);
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
	public DoubleMatrix parallel() {
		return parse("parallel-smoothing", DoubleMatrix::parse);
	}
	public DoubleMatrix orthogonal() {
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
		return parse(skeleton, "binarized-skeleton", BooleanMatrix::parse);
	}
	public BooleanMatrix binarizedSkeleton() {
		return pickSkeleton(this::binarizedSkeleton);
	}
	public BooleanMatrix thinned(SkeletonType skeleton) {
		return parse(skeleton, "thinned-skeleton", BooleanMatrix::parse);
	}
	public BooleanMatrix thinned() {
		return pickSkeleton(this::thinned);
	}
	public SkeletonGraph traced(SkeletonType skeleton) {
		return parse(skeleton, "traced-skeleton", SkeletonGraph::parse);
	}
	public SkeletonGraph traced() {
		return pickSkeleton(this::traced);
	}
	public SkeletonGraph dots(SkeletonType skeleton) {
		return parse(skeleton, "removed-dots", SkeletonGraph::parse);
	}
	public SkeletonGraph dots() {
		return pickSkeleton(this::dots);
	}
	public SkeletonGraph pores(SkeletonType skeleton) {
		return parse(skeleton, "removed-pores", SkeletonGraph::parse);
	}
	public SkeletonGraph pores() {
		return pickSkeleton(this::pores);
	}
	public SkeletonGraph gaps(SkeletonType skeleton) {
		return parse(skeleton, "removed-gaps", SkeletonGraph::parse);
	}
	public SkeletonGraph gaps() {
		return pickSkeleton(this::gaps);
	}
	public SkeletonGraph tails(SkeletonType skeleton) {
		return parse(skeleton, "removed-tails", SkeletonGraph::parse);
	}
	public SkeletonGraph tails() {
		return pickSkeleton(this::tails);
	}
	public SkeletonGraph fragments(SkeletonType skeleton) {
		return parse(skeleton, "removed-fragments", SkeletonGraph::parse);
	}
	public SkeletonGraph fragments() {
		return pickSkeleton(this::fragments);
	}
	public Template skeletonMinutiae() {
		return parse("skeleton-minutiae", Template::parse);
	}
	public Template innerMinutiae() {
		return parse("inner-minutiae", Template::parse);
	}
	public Template clouds() {
		return parse("removed-minutia-clouds", Template::parse);
	}
	public Template topMinutiae() {
		return parse("top-minutiae", Template::parse);
	}
	public Template shuffled() {
		return parse("shuffled-minutiae", Template::parse);
	}
	public EdgeTable edges() {
		return parse("edge-table", EdgeTable::parse);
	}
	public EdgeHash hash() {
		return parse("edge-hash", EdgeHash::parse);
	}
	public RootPairs roots() {
		return parse("roots", RootPairs::parse);
	}
	public int pairings() {
		return count(new TransparencyPath("pairing"));
	}
	public MatchPairing pairing(int offset) {
		return parse("pairing", offset, MatchPairing::parse);
	}
	public MatchPairing pairing() {
		return pairing(best().orElse(0));
	}
	public MatchScoring score(int offset) {
		return parse("score", offset, MatchScoring::parse);
	}
	public MatchScoring score() {
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
