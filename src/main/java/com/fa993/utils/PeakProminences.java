package com.fa993.utils;

import com.fa993.types.PeakProminenceOutput;

/**
 * Utility class for calculating the prominence of peaks in a signal.
 *
 * This class includes methods to validate parameters and compute the prominence of peaks,
 * which is a measure of how much a peak stands out relative to its surrounding signal.
 */
public class PeakProminences {

	/**
	 * Validates and ensures the input window length (`wlen`) is of type `int` and greater than 1.
	 *
	 * This method is used to process and validate the `wlen` parameter for peak prominence
	 * and width calculations.
	 *
	 * @param value The input value to validate and convert.
	 * @return The original `value` rounded up to an integer, or -1 if `value` is null (indicating no value was provided).
	 * @throws IllegalArgumentException if `value` is not larger than 1.
	 */
	public static int argWlenAsExpected(Integer value) {
		if (value == null) {
			// -1 signals that no value was supplied by the user
			return -1;
		} else if (value > 1) {
			// Round up to a positive integer
			return (int) Math.ceil(value);
		} else {
			throw new IllegalArgumentException("`wlen` must be larger than 1, was " + value);
		}
	}


	/**
	 * Calculates the prominence of each peak in a signal.
	 *
	 * Peak prominence is defined as the vertical distance between the peak and its lowest contour line,
	 * which is the higher of the lowest points to its left and right within the specified window.
	 *
	 * @param x     A signal array containing peaks to evaluate.
	 * @param peaks Indices of peaks in the signal array `x`.
	 * @param wlen  A window length in samples. The window length is rounded up to the nearest odd integer.
	 *              If `wlen` is smaller than 2, the entire signal `x` is used for prominence calculation.
	 * @return A {@link PeakProminenceOutput} object containing:
	 *         <ul>
	 *             <li>Array of prominences for each peak.</li>
	 *             <li>Array of left base indices for each peak.</li>
	 *             <li>Array of right base indices for each peak.</li>
	 *         </ul>
	 * @throws IllegalArgumentException if `x` or `peaks` is null, or if any value in `peaks` is an invalid index for `x`.
	 */
	public static PeakProminenceOutput call(double[] x, int[] peaks, int wlen) {
		if (x == null || peaks == null) {
			throw new IllegalArgumentException("Signal array and peaks array must not be null.");
		}

		int nPeaks = peaks.length;
		double[] prominences = new double[nPeaks];
		int[] leftBases = new int[nPeaks];
		int[] rightBases = new int[nPeaks];

		boolean showWarning = false;

		for (int peakIndex = 0; peakIndex < nPeaks; peakIndex++) {
			int peak = peaks[peakIndex];
			int iMin = 0;
			int iMax = x.length - 1;

			if (peak < iMin || peak > iMax) {
				throw new IllegalArgumentException("Peak " + peak + " is not a valid index for the signal array.");
			}

			if (wlen >= 2) {
				// Adjust window around the evaluated peak (within bounds)
				iMin = Math.max(peak - wlen / 2, iMin);
				iMax = Math.min(peak + wlen / 2, iMax);
			}

			// Find the left base in interval [iMin, peak]
			int i = peak;
			leftBases[peakIndex] = peak;
			double leftMin = x[peak];

			while (i >= iMin && x[i] <= x[peak]) {
				if (x[i] < leftMin) {
					leftMin = x[i];
					leftBases[peakIndex] = i;
				}
				i--;
			}

			// Find the right base in interval [peak, iMax]
			i = peak;
			rightBases[peakIndex] = peak;
			double rightMin = x[peak];

			while (i <= iMax && x[i] <= x[peak]) {
				if (x[i] < rightMin) {
					rightMin = x[i];
					rightBases[peakIndex] = i;
				}
				i++;
			}

			prominences[peakIndex] = x[peak] - Math.max(leftMin, rightMin);
			if (prominences[peakIndex] == 0) {
				showWarning = true;
			}
		}

		if (showWarning) {
			System.err.println("Warning: Some peaks have a prominence of 0.");
		}

		return new PeakProminenceOutput(prominences, leftBases, rightBases);
	}
}
