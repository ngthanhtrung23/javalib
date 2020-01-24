import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelSumTest {

	@Test
	void testSum() {
		int[] a = {1, 2, 3, 4, 5};
		ParallelSum sum = new ParallelSum(a);
		assertEquals(15, sum.compute());
	}

	@Test
	void testTime() {
		final int SIZE = 10_000_000;
		int[] a = new int[SIZE];
		Random rand = new Random();
		for (int i = 0; i < SIZE; i++) {
			a[i] = rand.nextInt();
		}

		measure(a, (input) -> {
			ParallelSum sum = new ParallelSum(input);
			return sum.compute();
		});
		measure(a, (input) -> {
			int result = 0;
			for (int value : input) {
				result += value;
			}
			return result;
		});
	}

	void measure(int[] input, Function<int[], Integer> func) {
		long startTimeNanos = System.nanoTime();
		Integer result = func.apply(input);
		long endTimeNanos = System.nanoTime();
		System.out.println((endTimeNanos - startTimeNanos) / 1e6 + " " + result);
	}
}
