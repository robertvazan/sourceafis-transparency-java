// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class MatchScoring {
	public int minutiaCount;
	public double minutiaScore;
	public double minutiaFractionInProbe;
	public double minutiaFractionInCandidate;
	public double minutiaFraction;
	public double minutiaFractionScore;
	public int supportingEdgeSum;
	public int edgeCount;
	public double edgeScore;
	public int supportedMinutiaCount;
	public double supportedMinutiaScore;
	public int minutiaTypeHits;
	public double minutiaTypeScore;
	public int distanceErrorSum;
	public int distanceAccuracySum;
	public double distanceAccuracyScore;
	public double angleErrorSum;
	public double angleAccuracySum;
	public double angleAccuracyScore;
	public double totalScore;
	public double shapedScore;
	public static MatchScoring parse(Map<String, Supplier<byte[]>> bundle) {
		return TransparencyArchive.parse(bundle.get(".cbor").get(), MatchScoring.class);
	}
}
