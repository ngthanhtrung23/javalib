import java.util.concurrent.RecursiveTask;

class ParallelSum extends RecursiveTask<Integer> {
	ParallelSum(int[] a, int low, int high) {
		this.a = a;
		this.low = low;
		this.high = high;
	}

	@Override
	protected Integer compute() {
		if (low + 10_000 > high) {
			int res = 0;
			for (int i = low; i <= high; i++) {
				res += a[i];
			}
			return res;
		}
		int mid = (low + high) / 2;
		ParallelSum left = new ParallelSum(a, low, mid);
		ParallelSum right = new ParallelSum(a, mid + 1, high);
		invokeAll(left, right);
		return left.join() + right.join();
	}

	ParallelSum(int[] a) {
		this.a = a;
		low = 0;
		high = a.length - 1;
	}

	private int[] a;
	private int low, high;
}
