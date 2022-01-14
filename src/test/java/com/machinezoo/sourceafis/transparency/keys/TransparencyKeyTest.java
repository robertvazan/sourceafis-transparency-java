// Part of SourceAFIS Transparency API: https://sourceafis.machinezoo.com/transparency/
package com.machinezoo.sourceafis.transparency.keys;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import com.machinezoo.sourceafis.transparency.*;

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
		var archive = TestResources.archive();
		for (var key : TransparencyKey.all())
			if (!(key instanceof TransparencyContextKey))
				assertTrue(archive.get(key).isPresent());
	}
	@Test
	public void coveringWholeArchive() {
		var archive = TestResources.archive();
		for (var key : archive.keys())
			assertThat(TransparencyKey.all(), hasItem(key));
	}
	@Test
	public void matchingMime() {
		var archive = TestResources.archive();
		for (var key : archive.keys())
			assertEquals(archive.get(key).get().mime(), key.mime());
	}
	@Test
	public void deserializable() {
		var archive = TestResources.archive();
		for (var key : archive.keys())
			assertNotNull(archive.get(key).get().deserialize());
	}
	private <T> byte[] roundtrip(TransparencyKey<T> key, byte[] data) {
		return key.serialize(key.deserialize(data));
	}
	@Test
	public void serializable() {
		var archive = TestResources.archive();
		for (var key : archive.keys())
			assertNotNull(roundtrip(key, archive.get(key).get().data()));
	}
}
