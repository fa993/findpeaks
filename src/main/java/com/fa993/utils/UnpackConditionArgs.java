package com.fa993.utils;

import com.fa993.types.supertype.Either;
import com.fa993.types.supertype.NumOrTwoSeqOrNdArr;
import com.fa993.types.supertype.PairOfDoubleOrDArr;

/**
 * Utility class for unpacking and transforming interval arguments into appropriate formats
 * based on specified conditions. This class processes a given interval and applies it to
 * specific indices of an array, ensuring the resulting structure is compatible with subsequent operations.
 */
public class UnpackConditionArgs {

	/**
	 * Unpacks the interval arguments into a pair of lower and upper boundaries, applying the interval
	 * conditions to a subset of data defined by the peaks.
	 *
	 * @param interval an object representing the interval, which can be:
	 *                 <ul>
	 *                     <li>A single scalar value.</li>
	 *                     <li>A pair of scalar values representing a range.</li>
	 *                     <li>A pair of arrays representing element-wise ranges.</li>
	 *                 </ul>
	 * @param x        the reference array containing the full dataset.
	 * @param peaks    an array of indices specifying the peaks to which the interval conditions should apply.
	 * @return a {@link PairOfDoubleOrDArr} object containing:
	 *         <ul>
	 *             <li>A lower boundary (`imin`) represented as a scalar or an array.</li>
	 *             <li>An upper boundary (`imax`) represented as a scalar or an array.</li>
	 *         </ul>
	 * @throws IllegalArgumentException if the size of the boundary arrays does not match the size of {@code x}.
	 * @throws RuntimeException         if the {@code interval} selector state is unknown.
	 */
	public static PairOfDoubleOrDArr call(NumOrTwoSeqOrNdArr interval, double[] x, int[] peaks) {
		Either.OfTwo<Double, double[]> imin = null, imax = null;

		if (interval.getSelector().isFirst()) {
			imin = Either.OfTwo.first(interval.getFirst());
		} else if(interval.getSelector().isSecond()) {
			imin = Either.OfTwo.first(interval.getSecond()[0]);
			imax = Either.OfTwo.first(interval.getSecond()[1]);
		} else if(interval.getSelector().isThird()) {
			imin = Either.OfTwo.second(interval.getThird()[0]);
			imax = Either.OfTwo.second(interval.getThird()[1]);
		} else {
			throw new RuntimeException("Unknown Selector for Either");
		}

		// Reduce arrays if necessary
		if (imin.getSelector().isSecond()) {
			double[] iminArray = imin.getSecond();
			if (iminArray.length != x.length) {
				throw new IllegalArgumentException("Array size of lower interval border must match x.");
			}
			imin = Either.OfTwo.second(extractValuesAtIndices(iminArray, peaks));
		}
		if (imax != null && imax.getSelector().isSecond()) {
			double[] imaxArray = imax.getSecond();
			if (imaxArray.length != x.length) {
				throw new IllegalArgumentException("Array size of upper interval border must match x.");
			}
			imax = Either.OfTwo.second(extractValuesAtIndices(imaxArray, peaks));
		}

		return new PairOfDoubleOrDArr(imin, imax);
	}

	/**
	 * Extracts values from a given array at the specified indices.
	 *
	 * @param array   the source array from which values will be extracted.
	 * @param indices an array of indices indicating which values to extract.
	 * @return a new array containing values from {@code array} at the specified indices.
	 */
	private static double[] extractValuesAtIndices(double[] array, int[] indices) {
		double[] reducedArray = new double[indices.length];
		for (int i = 0; i < indices.length; i++) {
			reducedArray[i] = array[indices[i]];
		}
		return reducedArray;
	}

}
