// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.*;
import com.machinezoo.noexception.*;

public class TransparencyArchiveTest {
	@Test public void getters() {
		TransparencyArchive archive = TransparencyBufferTest.capture();
		int count = 0;
		for (Method method : archive.getClass().getMethods()) {
			if (method.getParameterCount() == 0 && method.getDeclaringClass() != Object.class && method.getReturnType() != Void.TYPE) {
				++count;
				assertNotNull(method.getName(), Exceptions.sneak().get(() -> method.invoke(archive)));
			}
		}
		assertThat(count, greaterThan(20));
	}
}
