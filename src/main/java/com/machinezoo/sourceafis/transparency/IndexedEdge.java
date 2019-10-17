package com.machinezoo.sourceafis.transparency;

import java.nio.*;

public class IndexedEdge extends EdgeShape {
	public int reference;
	public int neighbor;
	IndexedEdge(ByteBuffer buffer) {
		reference = buffer.getInt();
		neighbor = buffer.getInt();
		length = buffer.getInt();
		referenceAngle = buffer.getDouble();
		neighborAngle = buffer.getDouble();
	}
}
