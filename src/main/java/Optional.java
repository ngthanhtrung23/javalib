import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;

public abstract class Optional<T> {
	public static <T> Optional<T> of(@NotNull T reference) {
		return new Present<T>(reference);
	}

	public static <T> Optional<T> absent() {
		return Absent.withType();
	}

	@SuppressWarnings("unchecked")
	public static <T> Optional<T> fromNullable(@Nullable T reference) {
		return (reference == null) ? (Optional<T>) absent() : of(reference);
	}

	public abstract boolean isPresent();
	public abstract T get();
	public abstract T or(@Nullable T defaultValue);
	public abstract Set<T> asSet();

	Optional() {}
}

class Absent<T> extends Optional<T> {
	// Because of type erasure, INSTANCE will just have type {@code Absent} in runtime.
	private static final Absent<Object> INSTANCE = new Absent<>();

	// {@code Absent<Object>} will not match {@code Optional<T>} for every T. However, it should be safe to cast,
	// because we don't really care about the type of T in {@code Absent}.
	@SuppressWarnings("unchecked")
	static<T> Optional<T> withType() {
		return (Optional<T>) INSTANCE;
	}

	@Override
	public boolean isPresent() {
		return false;
	}

	@Override
	public T get() {
		throw new IllegalStateException();
	}

	@Override
	public T or(@Nullable T defaultValue) {
		return defaultValue;
	}

	@Override
	public Set<T> asSet() {
		return Collections.emptySet();
	}

	private Absent() {}
}

class Present<T> extends Optional<T> {
	Present(T reference) {
		this.reference = reference;
	}

	@Override
	public boolean isPresent() {
		return true;
	}

	@Override
	public T get() {
		return reference;
	}

	@Override
	public T or(@Nullable T defaultValue) {
		return reference;
	}

	@Override
	public Set<T> asSet() {
		return Collections.singleton(reference);
	}

	private final T reference;
}
