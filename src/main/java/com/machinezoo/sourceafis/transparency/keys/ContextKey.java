// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import com.machinezoo.sourceafis.transparency.*;
import com.machinezoo.stagean.*;

@DraftApi("SourceAFIS will eventually log some context keys automatically, creating implicit context keys. Differentiate them from explicit keys.")
public interface ContextKey<T> extends TransparencyKey<T> {
}
