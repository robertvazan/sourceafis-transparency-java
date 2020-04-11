// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import java.util.regex.*;

public class TransparencyZipPath {
	private final String filename;
	public String filename() {
		return filename;
	}
	private final TransparencyPath parsed;
	public TransparencyPath parsed() {
		return parsed;
	}
	private final int sequence;
	public int sequence() {
		return sequence;
	}
	private final String suffix;
	public String suffix() {
		return suffix;
	}
	public String mime() {
		switch (suffix) {
		case ".cbor":
			return "application/cbor";
		case ".txt":
			return "text/plain";
		case ".json":
			return "application/json";
		case ".xml":
			return "application/xml";
		case ".jpeg":
			return "image/jpeg";
		case ".png":
			return "image/png";
		case ".bmp":
			return "image/bmp";
		case ".tiff":
			return "image/tiff";
		case ".jp2":
			return "image/jp2";
		case ".wsq":
			return "image/x-wsq";
		default:
			return "application/octet-stream";
		}
	}
	private static final Pattern filenameRe = Pattern.compile("^([0-9]+)-([a-z-]+)(\\.[a-z]+)$");
	public static boolean valid(String filename) {
		return filenameRe.matcher(filename).matches();
	}
	public TransparencyZipPath(String filename) {
		this.filename = filename;
		Matcher matcher = filenameRe.matcher(filename);
		if (!matcher.matches())
			throw new IllegalArgumentException();
		parsed = new TransparencyPath(matcher.group(2));
		sequence = Integer.parseInt(matcher.group(1));
		suffix = matcher.group(3);
	}
	@Override public boolean equals(Object obj) {
		return obj instanceof TransparencyZipPath && Objects.equals(filename, ((TransparencyZipPath)obj).filename);
	}
	@Override public int hashCode() {
		return filename.hashCode();
	}
}
