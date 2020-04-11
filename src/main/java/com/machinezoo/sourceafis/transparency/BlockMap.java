// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

public class BlockMap {
	public IntPoint pixels;
	public BlockGrid primary;
	public BlockGrid secondary;
	public static BlockMap parse(byte[] data) {
		return TransparencyArchive.parse(data, BlockMap.class);
	}
}
