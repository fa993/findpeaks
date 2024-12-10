package com.fa993.function.variations;

public class LocalMaximaJIU implements LocalMaxima {
	/**
	 * JavaInUse's output after putting numpy source for _local_maxima_1d
	 *
	 * @param x array of 1 dimensional points to find peaks for
	 * @return int[][] with the arr[0] array of midpoints of peaks, arr[1] array of left edges, arr[2] array of right edges
	 *
	 * @see <a href="https://github.com/scipy/scipy/blob/92d2a8592782ee19a1161d0bf3fc2241ba78bb63/scipy/signal/_peak_finding_utils.pyx#L20C1-L21C1">LocalMaxima source</a>
	 */
	@Override
	public int[][] localMaxima1D(double[] x) {
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

		return new int[][]{validMidpoints, validLeftEdges, validRightEdges};
	}
}
