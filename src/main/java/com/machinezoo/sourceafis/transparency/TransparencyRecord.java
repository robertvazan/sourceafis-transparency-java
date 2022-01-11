// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import java.util.*;
import org.apache.commons.lang3.*;
import com.machinezoo.sourceafis.transparency.keys.*;
import com.machinezoo.stagean.*;

@DraftCode("Nicer toString().")
public record TransparencyRecord<T>(TransparencyKey<T> key, String mime, byte[] data) {
	public TransparencyRecord {
		Objects.requireNonNull(key);
		Validate.notBlank(mime);
		Objects.requireNonNull(data);
	}
	public T deserialize() {
		return key.deserialize(mime, data);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof TransparencyRecord<?> other))
			return false;
		return key.equals(other.key) && mime.equals(other.mime) && Arrays.equals(data, other.data);
	}
	@Override
	public int hashCode() {
		return Arrays.deepHashCode(new Object[] { key, mime, data });
	}
}
