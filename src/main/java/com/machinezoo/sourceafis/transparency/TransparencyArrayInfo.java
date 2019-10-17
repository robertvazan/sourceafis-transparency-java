package com.machinezoo.sourceafis.transparency;

import com.google.gson.*;

public class TransparencyArrayInfo {
	public String[] axes;
	public int[] dimensions;
	public String scalar;
	public int bitness;
	public String endianness;
	public String format;
	public static TransparencyArrayInfo parse(String json) {
		return new Gson().fromJson(json, TransparencyArrayInfo.class);
	}
	public static TransparencyArrayInfo parse(byte[] buffer) {
		return parse(TransparencyUtils.text(buffer));
	}
}
