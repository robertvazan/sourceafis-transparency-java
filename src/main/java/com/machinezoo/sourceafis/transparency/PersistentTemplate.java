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
	public Template unpack() {
		Template unpacked = new Template();
		unpacked.size = new IntPoint(width, height);
		unpacked.minutiae = new TemplateMinutia[types.length()];
		for (int i = 0; i < types.length(); ++i) {
			TemplateMinutia minutia = new TemplateMinutia();
			minutia.position = new IntPoint(positionsX[i], positionsY[i]);
			minutia.direction = directions[i];
			minutia.type = types.charAt(i) == 'B' ? MinutiaType.BIFURCATION : MinutiaType.ENDING;
			unpacked.minutiae[i] = minutia;
		}
		return unpacked;
	}
	public static Template unpack(byte[] data) {
		return parse(data).unpack();
	}
}
