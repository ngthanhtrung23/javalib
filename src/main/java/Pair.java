import java.util.Objects;

public class Pair<F extends Comparable<F>, S extends Comparable<S>> implements Comparable<Pair<F, S>> {
	private final F first;
	private final S second;

	public static <F extends Comparable<F>, S extends Comparable<S>>
	Pair<F, S> of(F first, S second) {
		return new Pair<>(first, second);
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	private Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public int compareTo(Pair<F, S> o) {
		assert o != null;
		int c = first.compareTo(o.first);
		if (c != 0) {
			return c;
		}
		return second.compareTo(o.second);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pair<F, S> pair = (Pair<F, S>) o;
		return first.compareTo(pair.first) == 0
			&& second.compareTo(pair.second) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
}
