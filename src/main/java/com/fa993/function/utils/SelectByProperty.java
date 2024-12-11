package com.fa993.function.utils;
import com.fa993.function.supertype.Either;

import java.util.Arrays;


public class SelectByProperty {


	/**
	 * Evaluate where the generic property of peaks conforms to an interval.
	 *
	 * @param peakProperties An array with properties for each peak.
	 * @param pmin           Lower interval boundary for `peakProperties`. `null` is interpreted as an open border.
	 *                       It can be a single value or an array of the same length as `peakProperties`.
	 * @param pmax           Upper interval boundary for `peakProperties`. `null` is interpreted as an open border.
	 *                       It can be a single value or an array of the same length as `peakProperties`.
	 * @return A boolean array evaluating to true where `peakProperties` conforms to the interval.
	 * @throws IllegalArgumentException if `pmin` or `pmax` is an array of a different length than `peakProperties`.
	 */
	public static boolean[] call(int[] peakProperties, Either.OfTwo<Double, double[]> pmin, Either.OfTwo<Double, double[]> pmax) {
		double[] pminArray = (pmin == null) ? null : new double[peakProperties.length];
		double[] pmaxArray = (pmax == null) ? null : new double[peakProperties.length];

		if(pmin != null) {
			if (pmin.getSelector().isFirst()) {
				Arrays.fill(pminArray, pmin.getFirst());
			} else if (pmin.getSelector().isSecond()) {
				pminArray = pmin.getSecond();
			} else {
				throw new IllegalArgumentException("pmin selector state is unknown");
			}
		}

		if(pmax != null) {
			if (pmax.getSelector().isFirst()) {
				Arrays.fill(pmaxArray, pmax.getFirst());
			} else if(pmax.getSelector().isSecond()){
				pmaxArray = pmax.getSecond();
			} else {
				throw new IllegalArgumentException("pmax selector state is unknown");
			}
		}

		int n = peakProperties.length;
		boolean[] keep = new boolean[n];
		Arrays.fill(keep, true);

		// Validate input dimensions for pmin and pmax if they are arrays
		if (pminArray != null && pminArray.length != n) {
			throw new IllegalArgumentException("pmin array must be the same length as peakProperties");
		}
		if (pmaxArray != null && pmaxArray.length != n) {
			throw new IllegalArgumentException("pmax array must be the same length as peakProperties");
		}

		// Apply pmin boundary
		if (pminArray != null) {
			for (int i = 0; i < n; i++) {
				if (peakProperties[i] < pminArray[i]) {
					keep[i] = false;
				}
			}
		}

		// Apply pmax boundary
		if (pmaxArray != null) {
			for (int i = 0; i < n; i++) {
				if (peakProperties[i] > pmaxArray[i]) {
					keep[i] = false;
				}
			}
		}

		return keep;
	}

	public static boolean[] call(double[] peakProperties, Either.OfTwo<Double, double[]> pmin, Either.OfTwo<Double, double[]> pmax) {
		double[] pminArray = (pmin == null) ? null : new double[peakProperties.length];
		double[] pmaxArray = (pmax == null) ? null : new double[peakProperties.length];

		if(pmin != null) {
			if (pmin.getSelector().isFirst()) {
				Arrays.fill(pminArray, pmin.getFirst());
			} else if (pmin.getSelector().isSecond()) {
				pminArray = pmin.getSecond();
			} else {
				throw new IllegalArgumentException("pmin selector state is unknown");
			}
		}

		if(pmax != null) {
			if (pmax.getSelector().isFirst()) {
				Arrays.fill(pmaxArray, pmax.getFirst());
			} else if(pmax.getSelector().isSecond()){
				pmaxArray = pmax.getSecond();
			} else {
				throw new IllegalArgumentException("pmax selector state is unknown");
			}
		}

		int n = peakProperties.length;
		boolean[] keep = new boolean[n];
		Arrays.fill(keep, true);

		// Validate input dimensions for pmin and pmax if they are arrays
		if (pminArray != null && pminArray.length != n) {
			throw new IllegalArgumentException("pmin array must be the same length as peakProperties");
		}
		if (pmaxArray != null && pmaxArray.length != n) {
			throw new IllegalArgumentException("pmax array must be the same length as peakProperties");
		}

		// Apply pmin boundary
		if (pminArray != null) {
			for (int i = 0; i < n; i++) {
				if (peakProperties[i] < pminArray[i]) {
					keep[i] = false;
				}
			}
		}

		// Apply pmax boundary
		if (pmaxArray != null) {
			for (int i = 0; i < n; i++) {
				if (peakProperties[i] > pmaxArray[i]) {
					keep[i] = false;
				}
			}
		}

		return keep;
	}

//	/**
//	 * Overloaded method for scalar pmin and pmax values.
//	 */
//	public static boolean[] selectByProperty(double[] peakProperties, Double pmin, Double pmax) {
//		// Convert scalars to arrays
//		Double[] pminArray = (pmin == null) ? null : new Double[peakProperties.length];
//		Double[] pmaxArray = (pmax == null) ? null : new Double[peakProperties.length];
//
//		if (pminArray != null) {
//			Arrays.fill(pminArray, pmin);
//		}
//		if (pmaxArray != null) {
//			Arrays.fill(pmaxArray, pmax);
//		}
//
//		return selectByProperty(peakProperties, pminArray, pmaxArray);
//	}

}
