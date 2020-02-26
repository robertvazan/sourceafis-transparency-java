// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.*;
import org.junit.jupiter.api.*;
import com.machinezoo.noexception.*;

public class TransparencyArchiveTest {
	@Test public void getters() {
		TransparencyArchive archive = TransparencyBufferTest.capture();
		int count = 0;
		for (Method method : archive.getClass().getMethods()) {
			if (method.getParameterCount() == 0 && method.getDeclaringClass() != Object.class && method.getReturnType() != Void.TYPE) {
				++count;
				assertNotNull(Exceptions.sneak().get(() -> method.invoke(archive)), method.getName());
			}
		}
		assertThat(count, greaterThan(20));
	}
}
