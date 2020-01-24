import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
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
		final int SIZE = 100_000_000;
		int[] a = new int[SIZE];
		Random rand = new Random();
		for (int i = 0; i < SIZE; i++) {
			a[i] = rand.nextInt();
		}

		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

		System.out.println("n processors = " + Runtime.getRuntime().availableProcessors());
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "16");
		int parallelResult = measure("parallel", a, (input) -> {
			ParallelSum sum = new ParallelSum(input);
			return pool.invoke(sum);
		});
		int sequentialResult = measure("sequential", a, (input) -> {
			int result = 0;
			for (int value : input) {
				result += value;
			}
			return result;
		});
		assertEquals(parallelResult, sequentialResult);
	}

	int measure(String label, int[] input, Function<int[], Integer> func) {
		long startTimeNanos = System.nanoTime();
		Integer result = func.apply(input);
		long endTimeNanos = System.nanoTime();
		System.out.println(label + ": " + (endTimeNanos - startTimeNanos) / 1e6 + " " + result);
		return result;
	}
}
