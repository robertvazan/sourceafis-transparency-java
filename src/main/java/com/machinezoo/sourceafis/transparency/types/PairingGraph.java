// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.util.*;

public record PairingGraph(MinutiaPair root, EdgePair[] tree, EdgePair[] support) {
	public PairingGraph {
		Objects.requireNonNull(root, "Pairing graph is missing root.");
		Objects.requireNonNull(tree, "Pairing graph is missing pairing tree.");
		for (var pair : tree)
			Objects.requireNonNull(pair, "Tree edge is null.");
		Objects.requireNonNull(support, "Pairing graph is missing support edges.");
		for (var pair : support)
			Objects.requireNonNull(pair, "Support edge is null.");
	}
}
