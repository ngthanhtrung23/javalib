import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PairTest {
	@Test
	void pairInt() {
		Pair<Integer, Integer> p = Pair.of(1, 2);
		assertTrue(p.compareTo(Pair.of(1, 3)) < 0);
		assertTrue(p.compareTo(Pair.of(2, 1)) < 0);
		assertEquals(0, p.compareTo(Pair.of(1, 2)));
		assertTrue(p.compareTo(Pair.of(0, 3)) > 0);
		assertTrue(p.compareTo(Pair.of(1, 1)) > 0);

		assertEquals(1, p.getFirst());
		assertEquals(2, p.getSecond());
	}

	@Test
	void pairPair() {
		Pair<Pair<Integer, Double>, Pair<Integer, Double>> p = Pair.of(Pair.of(1, 2.0), Pair.of(3, 4.0));
		assertTrue(p.compareTo(Pair.of(Pair.of(1, 2.1), Pair.of(3, 4.0))) < 0);

		assertEquals(Pair.of(1, 2.0), p.getFirst());
		assertEquals(Pair.of(3, 4.0), p.getSecond());
	}
}
