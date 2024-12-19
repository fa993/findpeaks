package com.fa993.types.supertype;

/**
 * Represents a pair where each element is an instance of {@link Either.OfTwo} that can hold:
 * <ul>
 *     <li>A {@link Double} value</li>
 *     <li>An array of {@code double} values</li>
 * </ul>
 */
public class PairOfDoubleOrDArr extends Pair<Either.OfTwo<Double, double[]>, Either.OfTwo<Double, double[]>>{

	/**
	 * Constructs a {@code PairOfDoubleOrDArr} with the specified first and second elements.
	 *
	 * @param first  the first element of the pair, an instance of {@link Either.OfTwo}
	 * @param second the second element of the pair, an instance of {@link Either.OfTwo}
	 */
	public PairOfDoubleOrDArr(Either.OfTwo<Double, double[]> first, Either.OfTwo<Double, double[]> second) {
		super(first, second);
	}
}
