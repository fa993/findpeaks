package com.fa993.function.variations;

import java.util.ArrayList;
import java.util.List;

public class LocalMaximaCC implements LocalMaxima {

	/**
	 * CodeConvert output after putting numpy source for _local_maxima_1d
	 *
	 * @param x array of 1 dimensional points to find peaks for
	 * @return int[][] with the arr[0] array of midpoints of peaks, arr[1] array of left edges, arr[2] array of right edges
	 *
	 * @see <a href="https://github.com/scipy/scipy/blob/92d2a8592782ee19a1161d0bf3fc2241ba78bb63/scipy/signal/_peak_finding_utils.pyx#L20C1-L21C1">LocalMaxima source</a>
	 */
	@Override
	public int[][] localMaxima1D(double[] x) {
		if (x == null) {
			throw new IllegalArgumentException("Input array cannot be null");
		}

		int n = x.length;
		List<Integer> midpoints = new ArrayList<>();
		List<Integer> leftEdges = new ArrayList<>();
		List<Integer> rightEdges = new ArrayList<>();

		int m = 0; // Pointer to the end of valid area in allocated arrays

		int i = 1; // Pointer to current sample, first one can't be maxima
		int iMax = n - 1; // Last sample can't be maxima
		while (i < iMax) {
			// Test if previous sample is smaller
			if (x[i - 1] < x[i]) {
				int iAhead = i + 1; // Index to look ahead of current sample

				// Find next sample that is unequal to x[i]
				while (iAhead < iMax && x[iAhead] == x[i]) {
					iAhead++;
				}

				// Maxima is found if next unequal sample is smaller than x[i]
				if (iAhead < n && x[iAhead] < x[i]) {
					leftEdges.add(i);
					rightEdges.add(iAhead - 1);
					midpoints.add((leftEdges.get(m) + rightEdges.get(m)) / 2);
					m++;
					// Skip samples that can't be maximum
					i = iAhead;
				}
			}
			i++;
		}

		// Convert lists to arrays
		return new int[][] {midpoints.stream().mapToInt(t -> t).toArray(),
				leftEdges.stream().mapToInt(t -> t).toArray(),
				rightEdges.stream().mapToInt(t -> t).toArray()};
	}
}

