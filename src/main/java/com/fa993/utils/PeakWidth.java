package com.fa993.utils;

import com.fa993.types.PeakWidthsOutput;

/**
 * Utility class for calculating the widths of peaks in a given dataset.
 * The calculations are based on the prominence data and relative height.
 */
public class PeakWidth {

	/**
	 * Calculates the widths of peaks in the input data array using prominence information
	 * and a specified relative height. The method determines the left and right intersection
	 * points of the peaks at the specified height and calculates the width for each peak.
	 *
	 * @param x           the input data array containing the signal values.
	 * @param peaks       the indices of the peaks in the signal.
	 * @param relHeight   the relative height (a fraction of the prominence) at which to calculate widths.
	 * @param prominences the prominence values corresponding to the peaks.
	 * @param leftBases   the indices of the left bases for the peaks.
	 * @param rightBases  the indices of the right bases for the peaks.
	 * @return a {@link PeakWidthsOutput} object containing the calculated widths,
	 *         intersection points, and heights for the peaks.
	 * @throws IllegalArgumentException if:
	 *         <ul>
	 *         <li>`relHeight` is less than 0</li>
	 *         <li>The arrays `peaks`, `prominences`, `leftBases`, or `rightBases`
	 *             do not have the same length</li>
	 *         <li>The prominence data is invalid for any peak</li>
	 *         </ul>
	 */
	public static PeakWidthsOutput call(double[] x, int[] peaks, double relHeight, double[] prominences, int[] leftBases, int[] rightBases) {
		if (relHeight < 0) {
			throw new IllegalArgumentException("`relHeight` must be greater or equal to 0.0");
		}

		if (peaks.length != prominences.length || peaks.length != leftBases.length || peaks.length != rightBases.length) {
			throw new IllegalArgumentException("arrays in `prominence data` must have the same shape as `peaks`");
		}

		int n = peaks.length;
		double[] widths = new double[n];
		double[] widthHeights = new double[n];
		double[] leftIps = new double[n];
		double[] rightIps = new double[n];
		boolean showWarning = false;

		for (int p = 0; p < n; p++) {
			int iMin = leftBases[p];
			int iMax = rightBases[p];
			int peak = peaks[p];

			if (!(0 <= iMin && iMin <= peak && peak <= iMax && iMax < x.length)) {
				throw new IllegalArgumentException("prominence data is invalid for peak " + peak);
			}

			double height = x[peak] - prominences[p] * relHeight;
			widthHeights[p] = height;

			// Find intersection point on left side
			int i = peak;
			while (iMin < i && height < x[i]) {
				i--;
			}
			double leftIp = i;
			if (x[i] < height) {
				leftIp += (height - x[i]) / (x[i + 1] - x[i]);
			}

			// Find intersection point on right side
			i = peak;
			while (i < iMax && height < x[i]) {
				i++;
			}
			double rightIp = i;
			if (x[i] < height) {
				rightIp -= (height - x[i]) / (x[i - 1] - x[i]);
			}

			widths[p] = rightIp - leftIp;
			if (widths[p] == 0) {
				showWarning = true;
			}
			leftIps[p] = leftIp;
			rightIps[p] = rightIp;
		}

		if (showWarning) {
			System.err.println("Warning: Some peaks have a width of 0");
		}

		return new PeakWidthsOutput(widths, widthHeights, leftIps, rightIps);
	}
}
