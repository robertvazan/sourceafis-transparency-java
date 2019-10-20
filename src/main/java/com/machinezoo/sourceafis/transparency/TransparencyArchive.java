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
	public String version() {
		return JsonVersion.parse(json("version"));
	}
	public DoubleMatrix decoded() {
		return new DoubleMatrix(bundle("decoded-image"));
	}
	public DoubleMatrix scaled() {
		return new DoubleMatrix(bundle("scaled-image"));
	}
	public BlockMap blocks() {
		return BlockMap.parse(json("block-map"));
	}
	public HistogramCube histogram() {
		return new HistogramCube(bundle("histogram"));
	}
	public HistogramCube smoothedHistogram() {
		return new HistogramCube(bundle("smoothed-histogram"));
	}
	public DoubleMatrix contrast() {
		return new DoubleMatrix(bundle("clipped-contrast"));
	}
	public BooleanMatrix absoluteMask() {
		return new BooleanMatrix(bundle("absolute-contrast-mask"));
	}
	public BooleanMatrix relativeMask() {
		return new BooleanMatrix(bundle("relative-contrast-mask"));
	}
	public BooleanMatrix combinedMask() {
		return new BooleanMatrix(bundle("combined-mask"));
	}
	public BooleanMatrix filteredMask() {
		return new BooleanMatrix(bundle("filtered-mask"));
	}
	public DoubleMatrix equalized() {
		return new DoubleMatrix(bundle("equalized-image"));
	}
	public DoublePointMatrix pixelwiseOrientation() {
		return new DoublePointMatrix(bundle("pixelwise-orientation"));
	}
	public DoublePointMatrix blockOrientation() {
		return new DoublePointMatrix(bundle("block-orientation"));
	}
	public DoublePointMatrix smoothedOrientation() {
		return new DoublePointMatrix(bundle("smoothed-orientation"));
	}
	public DoubleMatrix parallelSmoothing() {
		return new DoubleMatrix(bundle("parallel-smoothing"));
	}
	public DoubleMatrix orthogonalSmoothing() {
		return new DoubleMatrix(bundle("orthogonal-smoothing"));
	}
	public BooleanMatrix binarized() {
		return new BooleanMatrix(bundle("binarized-image"));
	}
	public BooleanMatrix filteredBinary() {
		return new BooleanMatrix(bundle("filtered-binary-image"));
	}
	public BooleanMatrix pixelMask() {
		return new BooleanMatrix(bundle("pixel-mask"));
	}
	public BooleanMatrix innerMask() {
		return new BooleanMatrix(bundle("inner-mask"));
	}
	public TransparencyArchiveSkeleton ridges() {
		return new TransparencyArchiveSkeleton(this, "ridges-");
	}
	public TransparencyArchiveSkeleton valleys() {
		return new TransparencyArchiveSkeleton(this, "valleys-");
	}
	public Template skeletonMinutiae() {
		return new Template(json("skeleton-minutiae"));
	}
	public Template innerMinutiae() {
		return new Template(json("inner-minutiae"));
	}
	public Template removedMinutiaClouds() {
		return new Template(json("removed-minutia-clouds"));
	}
	public Template topMinutiae() {
		return new Template(json("top-minutiae"));
	}
	public Template shuffledMinutiae() {
		return new Template(json("shuffled-minutiae"));
	}
	public NeighborEdge[][] edgeTable() {
		return new Gson().fromJson(json("edge-table"), NeighborEdge[][].class);
	}
	public EdgeHash edgeHash() {
		return new EdgeHash(ByteBuffer.wrap(data("edge-hash")));
	}
	public MinutiaPair[] rootPairs() {
		return new Gson().fromJson(json("root-pairs"), MinutiaPair[].class);
	}
	public int pairingCount() {
		return (int)enumerate().stream()
			.filter(n -> new TransparencyPath(n).keyword().equals("pairing"))
			.count();
	}
	public MatchPairing pairing(int offset) {
		return MatchPairing.parse(json("pairing", offset));
	}
	public MatchPairing pairing() {
		return pairing(bestMatch());
	}
	public MatchScoring score(int offset) {
		return new Gson().fromJson(json("score", offset), MatchScoring.class);
	}
	public MatchScoring score() {
		return score(bestMatch());
	}
	public int bestMatch() {
		return JsonBestMatch.parse(json("best-match"));
	}
}
