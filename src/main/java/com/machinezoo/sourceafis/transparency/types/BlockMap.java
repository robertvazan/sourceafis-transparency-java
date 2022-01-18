// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.types;

import java.io.*;
import com.machinezoo.stagean.*;

@DraftCode("Implement full validation.")
public record BlockMap(IntPoint pixels, BlockGrid primary, BlockGrid secondary) implements Serializable {
}
