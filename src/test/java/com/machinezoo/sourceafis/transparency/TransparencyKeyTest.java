// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import com.machinezoo.sourceafis.transparency.keys.*;

public class TransparencyKeyTest {
	@Test
	public void unique() {
		assertEquals(TransparencyKey.all().size(), TransparencyKey.all().stream().distinct().count());
	}
	@Test
	public void uniqueNames() {
		assertEquals(TransparencyKey.all().size(), TransparencyKey.all().stream().map(k -> k.name()).distinct().count());
	}
	@Test
	public void presentInArchive() {
		var archive = TestArchives.universal();
		for (var key : TransparencyKey.all())
			if (!(key instanceof ContextKey))
				assertTrue(archive.contains(key));
	}
	@Test
	public void coveringWholeArchive() {
		for (var key : TestArchives.universal().keys())
			assertThat(TransparencyKey.all(), hasItem(key));
	}
	@Test
	public void mentionsOperation() {
		for (var operation : TransparentOperation.values()) {
			for (var key : TestArchives.full(operation).keys())
				assertThat(key.operations(), hasItem(operation));
		}
	}
	@Test
	public void generatedByOperation() {
		for (var key : TransparencyKey.all())
			if (!(key instanceof ContextKey))
				for (var operation : key.operations())
					assertThat(TestArchives.full(operation).keys(), hasItem(key));
	}
	@Test
	public void matchingMime() {
		var archive = TestArchives.universal();
		for (var key : archive.keys())
			assertEquals(archive.get(key).get().mime(), key.mime());
	}
	@Test
	public void deserializable() {
		var archive = TestArchives.universal();
		for (var key : archive.keys())
			assertNotNull(archive.deserialize(key).get());
	}
	private <T> byte[] roundtrip(TransparencyKey<T> key, byte[] data) {
		return key.serialize(key.deserialize(data));
	}
	@Test
	public void serializable() {
		var archive = TestArchives.universal();
		for (var key : archive.keys())
			assertNotNull(roundtrip(key, archive.read(key).get()));
	}
}
