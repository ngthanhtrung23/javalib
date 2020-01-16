import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.junit.jupiter.api.Assertions.*;

class OptionalTest {

	@Test
	void optionalOfInt() {
		Optional<Integer> optional = Optional.of(5);
		assertTrue(optional.isPresent());
		assertEquals(5, optional.get());
		assertEquals(5, optional.or(0));
		assertEquals(5, optional.or(null));
		assertEquals(1, optional.asSet().size());
		assertThat(optional.asSet(), hasItem(5));
	}

	@Test
	void optionalEmptyInt() {
		Optional<Integer> optional = Optional.absent();
		assertFalse(optional.isPresent());
		assertThrows(IllegalStateException.class, optional::get);
		assertEquals(0, optional.or(0));
		assertNull(optional.or(null));
		assertEquals(0, optional.asSet().size());
	}

	class Foo {
		public Foo(String value) {
			this.value = value;
		}
		String value;
	}

	@Test
	void optionalOfObj() {
		Foo foo = new Foo("foo");
		Optional<Foo> optional = Optional.of(foo);
		assertTrue(optional.isPresent());
		assertSame(foo, optional.get());
		assertSame(foo, optional.or(null));
		assertSame(foo, optional.or(new Foo("bar")));
		assertEquals(1, optional.asSet().size());
		assertThat(optional.asSet(), hasItem(foo));
	}

	@Test
	void optionalEmptyObj() {
		Optional<Foo> optional = Optional.absent();
		assertFalse(optional.isPresent());
		assertThrows(IllegalStateException.class, optional::get);
		assertNull(optional.or(null));
		Foo foo = new Foo("foo");
		assertSame(foo, optional.or(foo));
		assertEquals(0, optional.asSet().size());
	}
}
