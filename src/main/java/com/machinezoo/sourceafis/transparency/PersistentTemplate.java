package com.machinezoo.sourceafis.transparency;

public class PersistentTemplate {
	public String version;
	public int width;
	public int height;
	public int[] positionsX;
	public int[] positionsY;
	public double[] directions;
	public String types;
	public static PersistentTemplate parse(byte[] data) {
		return TransparencyArchive.parse(data, PersistentTemplate.class);
	}
	public MutableTemplate unpack() {
		MutableTemplate unpacked = new MutableTemplate();
		unpacked.size = new IntPoint(width, height);
		unpacked.minutiae = new MutableMinutia[types.length()];
		for (int i = 0; i < types.length(); ++i) {
			MutableMinutia minutia = new MutableMinutia();
			minutia.position = new IntPoint(positionsX[i], positionsY[i]);
			minutia.direction = directions[i];
			minutia.type = types.charAt(i) == 'B' ? MinutiaType.BIFURCATION : MinutiaType.ENDING;
			unpacked.minutiae[i] = minutia;
		}
		return unpacked;
	}
	public static MutableTemplate unpack(byte[] data) {
		return parse(data).unpack();
	}
}
