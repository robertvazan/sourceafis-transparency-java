// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;

public record ScoreBreakdown(
	int minutiaCount,
	double minutiaScore,
	double minutiaFractionInProbe,
	double minutiaFractionInCandidate,
	double minutiaFraction,
	double minutiaFractionScore,
	int supportingEdgeSum,
	int edgeCount,
	double edgeScore,
	int supportedMinutiaCount,
	double supportedMinutiaScore,
	int minutiaTypeHits,
	double minutiaTypeScore,
	int distanceErrorSum,
	int distanceAccuracySum,
	double distanceAccuracyScore,
	double angleErrorSum,
	double angleAccuracySum,
	double angleAccuracyScore,
	double totalScore,
	double shapedScore) implements Serializable {
}
