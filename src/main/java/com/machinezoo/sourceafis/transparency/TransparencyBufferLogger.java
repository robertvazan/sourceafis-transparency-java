// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import com.machinezoo.sourceafis.*;

class TransparencyBufferLogger extends FingerprintTransparency {
	private final TransparencyBuffer buffer;
	TransparencyBufferLogger(TransparencyBuffer buffer) {
		this.buffer = buffer;
	}
	@Override
	public boolean accepts(String key) {
		return buffer.accepts(key);
	}
	@Override
	public void take(String key, String mime, byte[] data) {
		buffer.take(key, mime, data);
	}
}
