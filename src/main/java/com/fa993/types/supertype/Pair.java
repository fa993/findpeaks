package com.fa993.types.supertype;

/**
 * A generic class representing a pair of two related objects.
 *
 * @param <P> the type of the first element
 * @param <Q> the type of the second element
 */
public class Pair<P, Q> {

	/**
	 * The first element of the pair.
	 */
	private P first;

	/**
	 * The second element of the pair.
	 */
	private Q second;

	/**
	 * Constructs a new {@code Pair} with the specified elements.
	 *
	 * @param first  the first element of the pair
	 * @param second the second element of the pair
	 */
	public Pair(P first, Q second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Gets the first element of the pair.
	 *
	 * @return the first element
	 */
	public P getFirst() {
		return first;
	}

	/**
	 * Gets the second element of the pair.
	 *
	 * @return the second element
	 */
	public Q getSecond() {
		return second;
	}
}
