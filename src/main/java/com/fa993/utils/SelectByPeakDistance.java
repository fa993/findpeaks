package com.fa993.utils;

import java.util.Arrays;

/**
 * Utility class for selecting peaks based on a specified minimum distance condition.
 * Peaks that do not meet the distance condition relative to higher-priority peaks are excluded.
 */
public class SelectByPeakDistance {

	/**
	 * Evaluates which peaks fulfill the minimum distance condition based on their priorities.
	 * Peaks are sorted by priority, and those that violate the distance constraint relative
	 * to higher-priority peaks are excluded.
	 *
	 * @param peaks    an array of indices representing the positions of the peaks.
	 * @param priority an array of priority values corresponding to each peak. A higher value
	 *                 indicates a higher priority for that peak.
	 * @param distance the minimum distance that peaks must be spaced apart.
	 * @return a boolean array where {@code true} indicates the corresponding peak fulfills
	 *         the distance condition and should be kept.
	 */
	public static boolean[] call(int[] peaks, double[] priority, double distance) {
		return gpt(peaks, priority, distance);
	}

	/**
	 * Internal implementation of the peak selection algorithm based on distance and priority.
	 *
	 * @param peaks    an array of indices representing the positions of the peaks.
	 * @param priority an array of priority values corresponding to each peak.
	 * @param distance the minimum distance that peaks must be spaced apart.
	 * @return a boolean array where {@code true} indicates the corresponding peak fulfills
	 *         the distance condition and should be kept.
	 */
	private static boolean[] gpt(int[] peaks, double[] priority, double distance) {
		int peaksSize = peaks.length;
		int distanceRounded = (int) Math.ceil(distance);
		boolean[] keep = new boolean[peaksSize];
		Arrays.fill(keep, true); // Initialize all to true

		// Create a map from priority order to original positions
		Integer[] priorityToPosition = new Integer[peaksSize];
		for (int i = 0; i < peaksSize; i++) {
			priorityToPosition[i] = i;
		}
		Arrays.sort(priorityToPosition, (a, b) -> Double.compare(priority[b], priority[a]));

		// Iterate peaks in descending order of priority
		for (int i = 0; i < peaksSize; i++) {
			int j = priorityToPosition[i]; // Current peak's index

			if (!keep[j]) {
				continue; // Skip if already marked as "don't keep"
			}

			// Check earlier peaks
			int k = j - 1;
			while (k >= 0 && peaks[j] - peaks[k] < distanceRounded) {
				keep[k] = false;
				k--;
			}

			// Check later peaks
			k = j + 1;
			while (k < peaksSize && peaks[k] - peaks[j] < distanceRounded) {
				keep[k] = false;
				k++;
			}
		}

		return keep;
	}

	/**
	 * Alternative implementation of the peak selection algorithm (incorrect version).
	 *
	 * @param peaks    an array of indices representing the positions of the peaks.
	 * @param priority an array of priority values corresponding to each peak.
	 * @param distance the minimum distance that peaks must be spaced apart.
	 * @return a boolean array where {@code true} indicates the corresponding peak fulfills
	 *         the distance condition and should be kept.
	 */
	private static boolean[] jiu(int[] peaks, double[] priority, double distance) {
		int peaksSize = peaks.length;
		int distance_ = (int) Math.ceil(distance);
		byte[] keep = new byte[peaksSize];
		Arrays.fill(keep, (byte) 1);
		int[] priorityToPosition = new int[peaksSize];
		for (int i = 0; i < peaksSize; i++) {
			priorityToPosition[i] = i;
		}
		for (int i = peaksSize - 1; i >= 0; i--) {
			int j = priorityToPosition[i];
			if (keep[j] == 0) {
				continue;
			}
			int k = j - 1;
			while (k >= 0 && peaks[j] - peaks[k] < distance_) {
				keep[k] = 0;
				k--;
			}
			k = j + 1;
			while (k < peaksSize && peaks[k] - peaks[j] < distance_) {
				keep[k] = 0;
				k++;
			}
		}
		boolean[] result = new boolean[peaksSize];
		for (int i = 0; i < peaksSize; i++) {
			result[i] = keep[i] == 1;
		}
		return result;
	}

	/**
	 * Another alternative implementation of the peak selection algorithm (incorrect version).
	 *
	 * @param peaks    an array of indices representing the positions of the peaks.
	 * @param priority an array of priority values corresponding to each peak.
	 * @param distance the minimum distance that peaks must be spaced apart.
	 * @return a boolean array where {@code true} indicates the corresponding peak fulfills
	 *         the distance condition and should be kept.
	 */
	private static boolean[] cc(int[] peaks, double[] priority, double distance) {
		int peaksSize = peaks.length;
		int distance_ = (int) Math.ceil(distance);
		boolean[] keep = new boolean[peaksSize]; // Prepare array of flags
		Arrays.fill(keep, true);

		// Create map from `i` (index for `peaks` sorted by `priority`) to `j` (index for `peaks` sorted by position).
		Integer[] priorityToPosition = new Integer[peaksSize];
		for (int i = 0; i < peaksSize; i++) {
			priorityToPosition[i] = i;
		}
		Arrays.sort(priorityToPosition, (a, b) -> Double.compare(priority[b], priority[a]));

		// Highest priority first -> iterate in reverse order (decreasing)
		for (int i = peaksSize - 1; i >= 0; i--) {
			int j = priorityToPosition[i];
			if (!keep[j]) {
				// Skip evaluation for peak already marked as "don't keep"
				continue;
			}

			int k = j - 1;
			// Flag "earlier" peaks for removal until minimal distance is exceeded
			while (k >= 0 && peaks[j] - peaks[k] < distance_) {
				keep[k] = false;
				k--;
			}

			k = j + 1;
			// Flag "later" peaks for removal until minimal distance is exceeded
			while (k < peaksSize && peaks[k] - peaks[j] < distance_) {
				keep[k] = false;
				k++;
			}
		}

		return keep; // Return as boolean array
	}

}
