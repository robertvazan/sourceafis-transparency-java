// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.*;
import com.machinezoo.sourceafis.*;

public class TransparencyBufferTest {
	@Test
	public void capture() {
		var buffer = TestResources.buffer();
		assertThat(buffer.keys().size(), greaterThanOrEqualTo(20));
	}
	@Test
	public void unzip() {
		var direct = TestResources.buffer();
		var zip = new ByteArrayOutputStream();
		TestResources.log(() -> FingerprintTransparency.zip(zip));
		var unzipped = new TransparencyBuffer();
		unzipped.unzip(new ByteArrayInputStream(zip.toByteArray()));
		assertEquals(direct.keys(), unzipped.keys());
		for (var key : direct.keys()) {
			assertEquals(direct.count(key), unzipped.count(key));
			for (int offset = 0; offset < direct.count(key); ++offset) {
				assertEquals(direct.mime(key, offset), unzipped.mime(key, offset));
				assertArrayEquals(direct.read(key, offset).get(), unzipped.read(key, offset).get());
			}
		}
	}
}
