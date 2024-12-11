package com.fa993.utils;

import com.fa993.types.PeakWidthsOutput;

public class PeakWidth {

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
