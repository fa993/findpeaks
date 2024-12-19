package com.fa993.utils;

import com.fa993.types.SelectThresholdOutput;
import com.fa993.types.supertype.Either;

import java.util.Arrays;

/**
 * Utility class for evaluating peaks in a dataset based on threshold conditions.
 * The thresholds can be specified as scalar values or arrays, and peaks that meet
 * the threshold criteria are selected.
 */
public class SelectByPeakThreshold {


	/**
	 * Evaluates which peaks in the input array fulfill the threshold condition.
	 * The method determines whether the differences between the peak value and its
	 * adjacent values (left and right) lie within the specified minimum and maximum thresholds.
	 *
	 * @param x     a 1-D array of values from which peaks are indexed.
	 * @param peaks an array of indices representing the positions of the peaks in {@code x}.
	 * @param tmin  the minimum threshold, specified as either:
	 *              <ul>
	 *                <li>A scalar value to be applied uniformly to all peaks.</li>
	 *                <li>An array of thresholds, where each value corresponds to a specific peak.</li>
	 *                <li>Null, indicating no minimum threshold is applied.</li>
	 *              </ul>
	 * @param tmax  the maximum threshold, specified as either:
	 *              <ul>
	 *                <li>A scalar value to be applied uniformly to all peaks.</li>
	 *                <li>An array of thresholds, where each value corresponds to a specific peak.</li>
	 *                <li>Null, indicating no maximum threshold is applied.</li>
	 *              </ul>
	 * @return a {@link SelectThresholdOutput} object containing:
	 *         <ul>
	 *           <li>A boolean array indicating which peaks fulfill the threshold condition.</li>
	 *           <li>The left and right thresholds for each peak.</li>
	 *         </ul>
	 */
	public static SelectThresholdOutput call(double[] x, int[] peaks, Either.OfTwo<Double, double[]> tmin, Either.OfTwo<Double, double[]> tmax){
		int n = peaks.length;

		// Convert scalar tmin and tmax to arrays, if they are scalar
		double[] tminArray = tmin != null ? (tmin.getSelector().isSecond() ? tmin.getSecond() : filled(n, tmin.getFirst())) : null;
		double[] tmaxArray = tmax != null ? (tmax.getSelector().isSecond() ? tmax.getSecond() : filled(n, tmax.getFirst())) : null;

		return selectByPeakThreshold(x, peaks, tminArray, tmaxArray);
	}

	/**
	 * Helper method to create an array filled with a specific value.
	 *
	 * @param n   the size of the array.
	 * @param val the value to fill the array with.
	 * @return an array of size {@code n} filled with {@code val}.
	 */
	private static double[] filled(int n, double val) {
		double[] arr = new double[n];
		Arrays.fill(arr, val);
		return arr;
	}

	/**
	 * Internal method for evaluating peaks against specified threshold conditions.
	 * This method directly works with array representations of the thresholds.
	 *
	 * @param x     a 1-D array of values from which peaks are indexed.
	 * @param peaks an array of indices representing the positions of the peaks in {@code x}.
	 * @param tmin  an array of minimum thresholds or null.
	 * @param tmax  an array of maximum thresholds or null.
	 * @return a {@link SelectThresholdOutput} object containing:
	 *         <ul>
	 *           <li>A boolean array indicating which peaks fulfill the threshold condition.</li>
	 *           <li>The left and right thresholds for each peak.</li>
	 *         </ul>
	 */
	private static SelectThresholdOutput selectByPeakThreshold(double[] x, int[] peaks, double[] tmin, double[] tmax) {
		int n = peaks.length;

		// Stack thresholds for min/max operations
		double[] leftThresholds = new double[n];
		double[] rightThresholds = new double[n];

		for (int i = 0; i < n; i++) {
			leftThresholds[i] = x[peaks[i]] - x[peaks[i] - 1];
			rightThresholds[i] = x[peaks[i]] - x[peaks[i] + 1];
		}

		boolean[] keep = new boolean[n];
		Arrays.fill(keep, true);

		if (tmin != null) {
			for (int i = 0; i < n; i++) {
				double minThreshold = Math.min(leftThresholds[i], rightThresholds[i]);
				keep[i] &= tmin[i] <= minThreshold;
			}
		}

		if (tmax != null) {
			for (int i = 0; i < n; i++) {
				double maxThreshold = Math.max(leftThresholds[i], rightThresholds[i]);
				keep[i] &= maxThreshold <= tmax[i];
			}
		}

		return new SelectThresholdOutput(keep, leftThresholds, rightThresholds);
	}


}
