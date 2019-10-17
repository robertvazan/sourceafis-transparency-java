package com.machinezoo.sourceafis.transparency;

import java.nio.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public abstract class TransparencyArchive {
	public abstract List<String> enumerate();
	public abstract byte[] read(String path);
	private String path(String numberless) {
		return path(numberless, 0);
	}
	private String path(String numberless, int offset) {
		return enumerate().stream()
			.map(TransparencyDataPath::new)
			.filter(p -> numberless.equals(p.typed() + p.suffix()))
			.skip(offset)
			.map(TransparencyDataPath::filename)
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
	public DoubleMap decoded() {
		return new DoubleMap(bundle("decoded-image"));
	}
	public DoubleMap scaled() {
		return new DoubleMap(bundle("scaled-image"));
	}
	public BlockMap blocks() {
		return BlockMap.parse(json("block-map"));
	}
	public HistogramVolume histogram() {
		return new HistogramVolume(bundle("histogram"));
	}
	public HistogramVolume smoothedHistogram() {
		return new HistogramVolume(bundle("smoothed-histogram"));
	}
	public DoubleMap contrast() {
		return new DoubleMap(bundle("clipped-contrast"));
	}
	public BooleanMap absoluteMask() {
		return new BooleanMap(bundle("absolute-contrast-mask"));
	}
	public BooleanMap relativeMask() {
		return new BooleanMap(bundle("relative-contrast-mask"));
	}
	public BooleanMap combinedMask() {
		return new BooleanMap(bundle("combined-mask"));
	}
	public BooleanMap filteredMask() {
		return new BooleanMap(bundle("filtered-mask"));
	}
	public DoubleMap equalized() {
		return new DoubleMap(bundle("equalized-image"));
	}
	public PointMap pixelwiseOrientation() {
		return new PointMap(bundle("pixelwise-orientation"));
	}
	public PointMap blockOrientation() {
		return new PointMap(bundle("block-orientation"));
	}
	public PointMap smoothedOrientation() {
		return new PointMap(bundle("smoothed-orientation"));
	}
	public DoubleMap parallelSmoothing() {
		return new DoubleMap(bundle("parallel-smoothing"));
	}
	public DoubleMap orthogonalSmoothing() {
		return new DoubleMap(bundle("orthogonal-smoothing"));
	}
	public BooleanMap binarized() {
		return new BooleanMap(bundle("binarized-image"));
	}
	public BooleanMap filteredBinary() {
		return new BooleanMap(bundle("filtered-binary-image"));
	}
	public BooleanMap pixelMask() {
		return new BooleanMap(bundle("pixel-mask"));
	}
	public BooleanMap innerMask() {
		return new BooleanMap(bundle("inner-mask"));
	}
	public TransparencyArchiveSkeleton ridges() {
		return new TransparencyArchiveSkeleton(this, "ridges-");
	}
	public TransparencyArchiveSkeleton valleys() {
		return new TransparencyArchiveSkeleton(this, "valleys-");
	}
	public SavedTemplate skeletonMinutiae() {
		return new SavedTemplate(json("skeleton-minutiae"));
	}
	public SavedTemplate innerMinutiae() {
		return new SavedTemplate(json("inner-minutiae"));
	}
	public SavedTemplate removedMinutiaClouds() {
		return new SavedTemplate(json("removed-minutia-clouds"));
	}
	public SavedTemplate topMinutiae() {
		return new SavedTemplate(json("top-minutiae"));
	}
	public SavedTemplate shuffledMinutiae() {
		return new SavedTemplate(json("shuffled-minutiae"));
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
			.filter(n -> new TransparencyDataPath(n).keyword().equals("pairing"))
			.count();
	}
	public MatchPairing pairing(int offset) {
		return MatchPairing.parse(json("pairing", offset));
	}
	public MatchScoring score(int offset) {
		return new Gson().fromJson(json("score", offset), MatchScoring.class);
	}
	public int bestMatch() {
		return JsonBestMatch.parse(json("best-match"));
	}
}
