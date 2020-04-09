// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.function.*;

public class TransparencyArrayInfo {
	public String[] axes;
	public int[] dimensions;
	public String scalar;
	public int bitness;
	public String endianness;
	public String format;
	public static TransparencyArrayInfo parse(Map<String, Supplier<byte[]>> bundle) {
		return TransparencyArchive.parse(bundle.get(".cbor").get(), TransparencyArrayInfo.class);
	}
}
