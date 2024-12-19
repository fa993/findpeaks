package com.fa993.utils;
import com.fa993.types.supertype.Either;

import java.util.Arrays;

/**
 * Utility class for evaluating properties of peaks against defined intervals.
 * This class provides methods to filter peaks based on their properties conforming to specified
 * minimum and maximum boundary conditions. These boundaries can be scalars or arrays.
 */
public class SelectByProperty {


	/**
	 * Evaluates which peaks conform to a specified interval based on their properties.
	 * The interval is defined by optional minimum and maximum boundaries.
	 *
	 * @param peakProperties an array representing the properties of each peak.
	 * @param pmin           the lower boundary of the interval. This can be:
	 *                       <ul>
	 *                         <li>A scalar value applied uniformly to all peaks.</li>
	 *                         <li>An array of values, where each corresponds to a specific peak.</li>
	 *                         <li>Null, indicating no lower boundary is applied.</li>
	 *                       </ul>
	 * @param pmax           the upper boundary of the interval. This can be:
	 *                       <ul>
	 *                         <li>A scalar value applied uniformly to all peaks.</li>
	 *                         <li>An array of values, where each corresponds to a specific peak.</li>
	 *                         <li>Null, indicating no upper boundary is applied.</li>
	 *                       </ul>
	 * @return a boolean array where each element evaluates to {@code true} if the corresponding peak conforms
	 *         to the interval, and {@code false} otherwise.
	 * @throws IllegalArgumentException if {@code pmin} or {@code pmax} is an array of a different length
	 *                                  than {@code peakProperties}.
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

	/**
	 * Evaluates which peaks conform to a specified interval based on their properties.
	 * The interval is defined by optional minimum and maximum boundaries.
	 * This method operates on properties represented as double arrays.
	 *
	 * @param peakProperties an array of double values representing the properties of each peak.
	 * @param pmin           the lower boundary of the interval. This can be:
	 *                       <ul>
	 *                         <li>A scalar value applied uniformly to all peaks.</li>
	 *                         <li>An array of values, where each corresponds to a specific peak.</li>
	 *                         <li>Null, indicating no lower boundary is applied.</li>
	 *                       </ul>
	 * @param pmax           the upper boundary of the interval. This can be:
	 *                       <ul>
	 *                         <li>A scalar value applied uniformly to all peaks.</li>
	 *                         <li>An array of values, where each corresponds to a specific peak.</li>
	 *                         <li>Null, indicating no upper boundary is applied.</li>
	 *                       </ul>
	 * @return a boolean array where each element evaluates to {@code true} if the corresponding peak conforms
	 *         to the interval, and {@code false} otherwise.
	 * @throws IllegalArgumentException if {@code pmin} or {@code pmax} is an array of a different length
	 *                                  than {@code peakProperties}.
	 */
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

}
