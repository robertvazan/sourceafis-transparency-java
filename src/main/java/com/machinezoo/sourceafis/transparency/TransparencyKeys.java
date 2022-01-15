// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static java.util.stream.Collectors.*;
import java.util.*;
import com.machinezoo.sourceafis.transparency.keys.*;
import com.machinezoo.sourceafis.transparency.types.*;

class TransparencyKeys {
	static final List<TransparencyKey<?>> ALL;
	static final Map<String, TransparencyKey<?>> BY_NAME;
	static {
		var all = new ArrayList<TransparencyKey<?>>();
		all.addAll(List.of(
			new InputImageKey(),
			new InputGrayscaleKey(),
			new VersionKey(),
			new DecodedImageKey(),
			new ScaledImageKey(),
			new BlocksKey(),
			new HistogramKey(),
			new SmoothedHistogramKey(),
			new ContrastKey(),
			new AbsoluteContrastMaskKey(),
			new RelativeContrastMaskKey(),
			new CombinedMaskKey(),
			new FilteredMaskKey(),
			new EqualizedImageKey(),
			new PixelwiseOrientationKey(),
			new BlockOrientationKey(),
			new SmoothedOrientationKey(),
			new ParallelSmoothingKey(),
			new OrthogonalSmoothingKey(),
			new BinarizedImageKey(),
			new FilteredBinaryImageKey(),
			new PixelMaskKey(),
			new InnerMaskKey()));
		for (var skeleton : SkeletonType.values()) {
			all.addAll(List.of(
				new BinarizedSkeletonKey(skeleton),
				new ThinnedSkeletonKey(skeleton),
				new TracedSkeletonKey(skeleton),
				new RemovedDotsKey(skeleton),
				new RemovedPoresKey(skeleton),
				new RemovedGapsKey(skeleton),
				new RemovedTailsKey(skeleton),
				new RemovedFragmentsKey(skeleton)));
		}
		all.addAll(List.of(
			new SkeletonMinutiaeKey(),
			new InnerMinutiaeKey(),
			new RemovedMinutiaCloudsKey(),
			new TopMinutiaeKey(),
			new ShuffledMinutiaeKey(),
			new EdgeTableKey(),
			new OutputTemplateKey(),
			new InputTemplateKey(),
			new EdgeHashKey(),
			new ProbeImageKey(),
			new ProbeGrayscaleKey(),
			new ProbeTemplateKey(),
			new CandidateImageKey(),
			new CandidateGrayscaleKey(),
			new CandidateTemplateKey(),
			new RootsKey(),
			new PairingKey(),
			new ScoreKey(),
			new BestPairingKey(),
			new BestScoreKey(),
			new BestMatchKey(),
			new OutputScoreKey()));
		ALL = Collections.unmodifiableList(all);
		BY_NAME = all.stream().collect(toMap(k -> k.name(), k -> k));
	}
}
