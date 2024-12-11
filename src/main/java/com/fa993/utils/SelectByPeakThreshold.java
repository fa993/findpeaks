package com.fa993.utils;

import com.fa993.types.SelectThresholdOutput;
import com.fa993.types.supertype.Either;

import java.util.Arrays;

public class SelectByPeakThreshold {


	/**
	 * Evaluate which peaks fulfill the threshold condition.
	 *
	 * @param x     A 1-D array which is indexable by `peaks`.
	 * @param peaks Indices of peaks in `x`.
	 * @param tmin  Minimal required thresholds as an array, or null, or a scalar
	 * @param tmax  Maximal required thresholds as an array, or null, or a scalar
	 * @return A Result object containing the boolean mask and the left and right thresholds.
	 */
	public static SelectThresholdOutput call(double[] x, int[] peaks, Either.OfTwo<Double, double[]> tmin, Either.OfTwo<Double, double[]> tmax){
		int n = peaks.length;

		// Convert scalar tmin and tmax to arrays, if they are scalar
		double[] tminArray = tmin != null ? (tmin.getSelector().isSecond() ? tmin.getSecond() : filled(n, tmin.getFirst())) : null;
		double[] tmaxArray = tmax != null ? (tmax.getSelector().isSecond() ? tmax.getSecond() : filled(n, tmax.getFirst())) : null;

		return selectByPeakThreshold(x, peaks, tminArray, tmaxArray);
	}

	private static double[] filled(int n, double val) {
		double[] arr = new double[n];
		Arrays.fill(arr, val);
		return arr;
	}

	/**
	 * Evaluate which peaks fulfill the threshold condition.
	 *
	 * @param x     A 1-D array which is indexable by `peaks`.
	 * @param peaks Indices of peaks in `x`.
	 * @param tmin  Minimal required thresholds as an array, or null.
	 * @param tmax  Maximal required thresholds as an array, or null.
	 * @return A SelectThresholdOutput object containing the boolean mask and the left and right thresholds.
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
