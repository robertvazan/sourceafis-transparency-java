// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;
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
	String json(String bare) {
		return json(bare, 0);
	}
	String json(String bare, int offset) {
		String path = path(bare + ".json", offset);
		if (path == null)
			return null;
		byte[] binary = read(path);
		if (binary == null)
			return null;
		return new String(binary, StandardCharsets.UTF_8);
	}
	byte[] data(String bare) {
		String path = path(bare + ".dat");
		if (path == null)
			return null;
		return read(path);
	}
	static <T, R> R decode(T serialized, Function<T, R> decoder) {
		if (serialized == null)
			return null;
		return decoder.apply(serialized);
	}
	public String version() {
		return decode(json("version"), JsonVersion::parse);
	}
	public DoubleMatrix decoded() {
		return decode(bundle("decoded-image"), DoubleMatrix::new);
	}
	public DoubleMatrix scaled() {
		return decode(bundle("scaled-image"), DoubleMatrix::new);
	}
	public BlockMap blocks() {
		return decode(json("block-map"), BlockMap::parse);
	}
	public HistogramCube histogram() {
		return decode(bundle("histogram"), HistogramCube::new);
	}
	public HistogramCube smoothedHistogram() {
		return decode(bundle("smoothed-histogram"), HistogramCube::new);
	}
	public DoubleMatrix contrast() {
		return decode(bundle("clipped-contrast"), DoubleMatrix::new);
	}
	public BooleanMatrix absoluteMask() {
		return decode(bundle("absolute-contrast-mask"), BooleanMatrix::new);
	}
	public BooleanMatrix relativeMask() {
		return decode(bundle("relative-contrast-mask"), BooleanMatrix::new);
	}
	public BooleanMatrix combinedMask() {
		return decode(bundle("combined-mask"), BooleanMatrix::new);
	}
	public BooleanMatrix filteredMask() {
		return decode(bundle("filtered-mask"), BooleanMatrix::new);
	}
	public DoubleMatrix equalized() {
		return decode(bundle("equalized-image"), DoubleMatrix::new);
	}
	public DoublePointMatrix pixelwiseOrientation() {
		return decode(bundle("pixelwise-orientation"), DoublePointMatrix::new);
	}
	public DoublePointMatrix blockOrientation() {
		return decode(bundle("block-orientation"), DoublePointMatrix::new);
	}
	public DoublePointMatrix smoothedOrientation() {
		return decode(bundle("smoothed-orientation"), DoublePointMatrix::new);
	}
	public DoubleMatrix parallelSmoothing() {
		return decode(bundle("parallel-smoothing"), DoubleMatrix::new);
	}
	public DoubleMatrix orthogonalSmoothing() {
		return decode(bundle("orthogonal-smoothing"), DoubleMatrix::new);
	}
	public BooleanMatrix binarized() {
		return decode(bundle("binarized-image"), BooleanMatrix::new);
	}
	public BooleanMatrix filteredBinary() {
		return decode(bundle("filtered-binary-image"), BooleanMatrix::new);
	}
	public BooleanMatrix pixelMask() {
		return decode(bundle("pixel-mask"), BooleanMatrix::new);
	}
	public BooleanMatrix innerMask() {
		return decode(bundle("inner-mask"), BooleanMatrix::new);
	}
	public TransparencyArchiveSkeleton ridges() {
		return new TransparencyArchiveSkeleton(this, "ridges-");
	}
	public TransparencyArchiveSkeleton valleys() {
		return new TransparencyArchiveSkeleton(this, "valleys-");
	}
	public Template skeletonMinutiae() {
		return decode(json("skeleton-minutiae"), Template::new);
	}
	public Template innerMinutiae() {
		return decode(json("inner-minutiae"), Template::new);
	}
	public Template removedMinutiaClouds() {
		return decode(json("removed-minutia-clouds"), Template::new);
	}
	public Template topMinutiae() {
		return decode(json("top-minutiae"), Template::new);
	}
	public Template shuffledMinutiae() {
		return decode(json("shuffled-minutiae"), Template::new);
	}
	public NeighborEdge[][] edgeTable() {
		return decode(json("edge-table"), j -> new Gson().fromJson(j, NeighborEdge[][].class));
	}
	public EdgeHash edgeHash() {
		return decode(data("edge-hash"), d -> new EdgeHash(ByteBuffer.wrap(d)));
	}
	public MinutiaPair[] rootPairs() {
		return decode(json("root-pairs"), j -> new Gson().fromJson(j, MinutiaPair[].class));
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
		return decode(json("score", offset), j -> new Gson().fromJson(j, MatchScoring.class));
	}
	public MatchScoring score() {
		return score(bestMatch().orElse(0));
	}
	public OptionalInt bestMatch() {
		String json = json("best-match");
		return json != null ? OptionalInt.of(JsonBestMatch.parse(json)) : OptionalInt.empty();
	}
}
