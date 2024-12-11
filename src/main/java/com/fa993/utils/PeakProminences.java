package com.fa993.utils;

import com.fa993.types.PeakProminenceOutput;

public class PeakProminences {



	/**
	 * Ensure argument `wlen` is of type `int` and larger than 1.
	 *
	 * Used in peakProminences and peakWidths.
	 *
	 * @param value The input value to validate and convert.
	 * @return The original `value` rounded up to an integer or -1 if `value` was null.
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
	 * Calculate the prominence of each peak in a signal.
	 *
	 * @param x        A signal array with peaks.
	 * @param peaks    Indices of peaks in `x`.
	 * @param wlen     A window length in samples which is rounded up to the nearest odd integer.
	 *                 If smaller than 2, the entire signal `x` is used.
	 * @return A result object containing the prominences and the left and right bases for each peak.
	 * @throws IllegalArgumentException If a value in `peaks` is an invalid index for `x`.
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
