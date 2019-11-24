// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import com.google.gson.*;

public class MatchScoring {
	public int matchedMinutiae;
	public double matchedMinutiaeScore;
	public double matchedFractionOfProbeMinutiae;
	public double matchedFractionOfCandidateMinutiae;
	public double matchedFractionOfAllMinutiaeScore;
	public int matchedEdges;
	public double matchedEdgesScore;
	public int minutiaeWithSeveralEdges;
	public double minutiaeWithSeveralEdgesScore;
	public int correctMinutiaTypeCount;
	public double correctMinutiaTypeScore;
	public double accurateEdgeLengthScore;
	public double accurateMinutiaAngleScore;
	public double totalScore;
	public double shapedScore;
	public static MatchScoring parse(Map<String, Supplier<byte[]>> bundle) {
		return new Gson().fromJson(new String(bundle.get(".json").get(), StandardCharsets.UTF_8), MatchScoring.class);
	}
}
