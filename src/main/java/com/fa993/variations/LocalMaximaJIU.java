package com.fa993.variations;

import com.fa993.types.LocalMaximaOutput;

/**
 * Implementation of the {@link LocalMaxima} interface using a method adapted from
 * the scipy library's numpy-based peak-finding utility.
 * <p>
 * This class provides functionality to find local maxima in a 1-dimensional array
 * of points. It identifies the peaks, their midpoints, and their left and right edges.
 * </p>
 *
 * Generated Using JavaInUse
 *
 * @see <a href="https://github.com/scipy/scipy/blob/92d2a8592782ee19a1161d0bf3fc2241ba78bb63/scipy/signal/_peak_finding_utils.pyx#L20C1-L21C1">Original LocalMaxima source</a>
 */
public class LocalMaximaJIU implements LocalMaxima {
	/**
	 * Finds local maxima in a 1-dimensional array of points.
	 * <p>
	 * This method examines a given array of doubles and identifies the indices of local peaks.
	 * A peak is defined as a value that is greater than its neighbors. For plateaus (flat regions),
	 * the peak is considered valid if it transitions to lower values on both sides.
	 * </p>
	 *
	 * @param x an array of doubles representing 1-dimensional data points to find peaks for
	 * @return a {@link LocalMaximaOutput} containing:
	 *         <ul>
	 *         <li>Array of midpoints of peaks</li>
	 *         <li>Array of left edges of peaks</li>
	 *         <li>Array of right edges of peaks</li>
	 *         </ul>
	 *
	 * @see <a href="https://github.com/scipy/scipy/blob/92d2a8592782ee19a1161d0bf3fc2241ba78bb63/scipy/signal/_peak_finding_utils.pyx#L20C1-L21C1">LocalMaxima source</a>
	 */
	@Override
	public LocalMaximaOutput localMaxima1D(double[] x) {
		int[] midpoints = new int[x.length / 2];
		int[] leftEdges = new int[x.length / 2];
		int[] rightEdges = new int[x.length / 2];
		int m = 0;

		int i = 1;
		int iMax = x.length - 1;
		while (i < iMax) {
			if (x[i - 1] < x[i]) {
				int iAhead = i + 1;
				while (iAhead < iMax && x[iAhead] == x[i]) {
					iAhead++;
				}
				if (x[iAhead] < x[i]) {
					leftEdges[m] = i;
					rightEdges[m] = iAhead - 1;
					midpoints[m] = (leftEdges[m] + rightEdges[m]) / 2;
					m++;
					i = iAhead;
				}
			}
			i++;
		}

		int[] validMidpoints = new int[m];
		int[] validLeftEdges = new int[m];
		int[] validRightEdges = new int[m];
		System.arraycopy(midpoints, 0, validMidpoints, 0, m);
		System.arraycopy(leftEdges, 0, validLeftEdges, 0, m);
		System.arraycopy(rightEdges, 0, validRightEdges, 0, m);

		return new LocalMaximaOutput(validMidpoints, validLeftEdges, validRightEdges);
	}
}
