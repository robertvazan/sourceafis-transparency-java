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
		assertThat(TestResources.archive().keys().size(), greaterThanOrEqualTo(20));
	}
	@Test
	public void unzip() {
		var direct = TestResources.archive();
		var zip = new ByteArrayOutputStream();
		TestResources.log(() -> FingerprintTransparency.zip(zip));
		var unzipped = new TransparencyBuffer()
			.unzip(new ByteArrayInputStream(zip.toByteArray()))
			.toArchive();
		assertEquals(direct.toList(), unzipped.toList());
	}
}
