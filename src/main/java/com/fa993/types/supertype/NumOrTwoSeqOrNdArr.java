package com.fa993.types.supertype;

/**
 * Represents a specialized version of {@link Either.OfThree} that can hold one of three types:
 * <ul>
 *     <li>A {@link Double} value</li>
 *     <li>An array of two {@code double} values</li>
 *     <li>A 2D array of {@code double} values</li>
 * </ul>
 * This class enforces specific constraints on the second type, requiring it to be a sequence of exactly two doubles.
 */
public class NumOrTwoSeqOrNdArr extends Either.OfThree<Double, double[], double[][]>{

	/**
	 * Constructs a {@code NumOrTwoSeqOrNdArr} instance with the specified value and selector.
	 *
	 * @param first    the {@link Double} value, or {@code null} if not selected
	 * @param second   the array of two {@code double} values, or {@code null} if not selected
	 * @param third    the 2D array of {@code double} values, or {@code null} if not selected
	 * @param selector the {@link Either.Selector} indicating the selected type
	 */
	protected NumOrTwoSeqOrNdArr(Double first, double[] second, double[][] third, Either.Selector selector) {
		super(first, second, third, selector);
	}

	/**
	 * Creates a {@code NumOrTwoSeqOrNdArr} instance with the {@link Double} value selected.
	 *
	 * @param first the {@link Double} value (must not be {@code null})
	 * @return a {@code NumOrTwoSeqOrNdArr} instance with the {@link Double} value selected
	 * @throws NullPointerException if {@code first} is {@code null}
	 */
	public static NumOrTwoSeqOrNdArr first(Double first) {
		if (first == null) {
			throw new NullPointerException("first is marked non-null but is null");
		}
		return new NumOrTwoSeqOrNdArr(first, null, null, Either.Selector.First);
	}

	/**
	 * Creates a {@code NumOrTwoSeqOrNdArr} instance with the array of two {@code double} values selected.
	 *
	 * @param second the array of two {@code double} values (must not be {@code null}, and must have a length of 2)
	 * @return a {@code NumOrTwoSeqOrNdArr} instance with the array of two {@code double} values selected
	 * @throws NullPointerException     if {@code second} is {@code null}
	 * @throws IllegalArgumentException if {@code second} does not have a length of 2
	 */
	public static NumOrTwoSeqOrNdArr second(double[] second) {
		if (second == null) {
			throw new NullPointerException("second is marked non-null but is null");
		}
		if(second.length != 2) {
			throw new IllegalArgumentException("Sequence must be of length 2");
		}
		return new NumOrTwoSeqOrNdArr(null, second, null, Either.Selector.Second);
	}

	/**
	 * Creates a {@code NumOrTwoSeqOrNdArr} instance with the 2D array of {@code double} values selected.
	 *
	 * @param third the 2D array of {@code double} values (must not be {@code null})
	 * @return a {@code NumOrTwoSeqOrNdArr} instance with the 2D array of {@code double} values selected
	 * @throws NullPointerException if {@code third} is {@code null}
	 */
	public static NumOrTwoSeqOrNdArr third(double[][] third) {
		if (third == null) {
			throw new NullPointerException("third is marked non-null but is null");
		}
		return new NumOrTwoSeqOrNdArr(null, null, third, Either.Selector.Third);
	}
}