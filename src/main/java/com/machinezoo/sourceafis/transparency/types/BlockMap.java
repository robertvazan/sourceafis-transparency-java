// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

public record BlockMap(IntPoint pixels, BlockGrid primary, BlockGrid secondary) {
}
