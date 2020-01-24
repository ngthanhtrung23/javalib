import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelSumTest {

	@Test
	void testSum() {
		int[] a = {1, 2, 3, 4, 5};
		ParallelSum sum = new ParallelSum(a);
		assertEquals(15, sum.compute());
	}
}
