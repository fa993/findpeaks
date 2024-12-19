package com.fa993;

import com.fa993.types.*;
import com.fa993.types.supertype.NumOrTwoSeqOrNdArr;
import com.fa993.types.supertype.PairOfDoubleOrDArr;
import com.fa993.utils.*;
import com.fa993.variations.LocalMaxima;
import com.fa993.variations.LocalMaximaJIU;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for finding peaks in a 1D signal.
 *
 * This class provides methods to detect peaks in a signal using various conditions
 * such as height, threshold, distance, prominence, width, and plateau size.
 * The primary method is designed to mimic the behavior of Python's SciPy `find_peaks` function.
 *
 * The class uses the {@link LocalMaxima} interface for detecting local maxima,
 * with a default implementation provided by {@link LocalMaximaJIU}.
 */
public class FindPeaks {
	// using JavaInUse's local maxima impl by default
	private LocalMaxima lm = new LocalMaximaJIU();

	/**
	 * Default constructor. Uses the {@link LocalMaximaJIU} implementation for detecting local maxima.
	 */
	public FindPeaks() {}

	/**
	 * Constructor that allows specifying a custom {@link LocalMaxima} implementation.
	 *
	 * @param impl The custom implementation of the {@link LocalMaxima} interface.
	 */
	public FindPeaks(LocalMaxima impl) {
		this.lm = impl;
	}

	/**
	 * Finds peaks in a 1D signal.
	 *
	 * @param points The input signal array.
	 * @return An output object containing the indices of detected peaks and an empty properties map.
	 * @throws IllegalArgumentException If the input signal is null.
	 */
	public FindPeaksOutput call(double[] points) {
		return new FindPeaksOutput(lm.localMaxima1D(points).getMidpoints(), new HashMap<>());
	}

	/**
	 * Finds peaks in a 1D signal with various optional conditions and properties.
	 *
	 * This method detects peaks in the input signal and applies additional filtering based
	 * on the provided conditions such as height, threshold, distance, prominence, width, and plateau size.
	 * It mimics Python's SciPy `find_peaks` function.
	 *
	 * @param x            The input signal array.
	 * @param height       Minimum and/or maximum height of peaks.
	 * @param threshold    Minimum and/or maximum threshold values for peak bases.
	 * @param distance     Minimum distance between peaks. Must be ≥ 1.
	 * @param prominence   Minimum and/or maximum prominence of peaks.
	 * @param width        Minimum and/or maximum width of peaks.
	 * @param wlen         Window length for prominence calculation. Rounded up to the nearest odd integer.
	 * @param relHeight    Relative height for width calculation (default: 0.5 if null).
	 * @param plateauSize  Minimum and/or maximum size of plateaus.
	 * @return An output object containing the indices of detected peaks and a properties map with additional details.
	 * @throws IllegalArgumentException If any input condition is invalid or incompatible with the input signal.
	 * @see <a href="https://github.com/scipy/scipy/blob/92d2a8592782ee19a1161d0bf3fc2241ba78bb63/scipy/signal/_peak_finding.py#L729">FindPeaks Source</a>
	 */
	public FindPeaksOutput call(double[] x, NumOrTwoSeqOrNdArr height, NumOrTwoSeqOrNdArr threshold, Double distance,
								NumOrTwoSeqOrNdArr prominence, NumOrTwoSeqOrNdArr width, Integer wlen, Double relHeight,
								NumOrTwoSeqOrNdArr plateauSize) {


		if (distance != null && distance < 1) {
			throw new IllegalArgumentException("`distance` must be greater or equal to 1");
		}

		if (relHeight == null) {
			relHeight = 0.5;
		}

		LocalMaximaOutput localMaxima = lm.localMaxima1D(x);
		int[] peaks = localMaxima.getMidpoints();
		int[] leftEdges = localMaxima.getLeftEdges();
		int[] rightEdges = localMaxima.getRightEdges();
		Map<String, Object> properties = new HashMap<>();

		if (plateauSize != null) {
			// Evaluate plateau size
			int[] plateauSizes = new int[rightEdges.length];
			for (int i = 0; i < rightEdges.length; i++) {
				plateauSizes[i] = rightEdges[i] - leftEdges[i] + 1;
			}
			PairOfDoubleOrDArr pminmax = UnpackConditionArgs.call(plateauSize, x, peaks);
			boolean[] keep = SelectByProperty.call(plateauSizes, pminmax.getFirst(), pminmax.getSecond());
			peaks = Filter.filterArray(peaks, keep);
			properties.put("plateau_sizes", plateauSizes);
			properties.put("left_edges", leftEdges);
			properties.put("right_edges", rightEdges);
			Filter.filterProperties(properties, keep);
		}

		if (height != null) {
			// Evaluate height condition
			double[] peakHeights = new double[peaks.length];
			for (int i = 0; i < peaks.length; i++) {
				peakHeights[i] = x[peaks[i]];
			}
			PairOfDoubleOrDArr hminmax = UnpackConditionArgs.call(height, x, peaks);
			boolean[] keep = SelectByProperty.call(peakHeights, hminmax.getFirst(), hminmax.getSecond());
			peaks = Filter.filterArray(peaks, keep);
			properties.put("peak_heights", peakHeights);
			Filter.filterProperties(properties, keep);
		}

		if (threshold != null) {
			// Evaluate threshold condition
			PairOfDoubleOrDArr tminmax = UnpackConditionArgs.call(threshold, x, peaks);
			// this function is tested by the global find peaks test, granular testing on this level involves too much effort compared to it's worth
			SelectThresholdOutput thresholdResults = SelectByPeakThreshold.call(x, peaks, tminmax.getFirst(), tminmax.getSecond());
			peaks = Filter.filterArray(peaks, thresholdResults.getKeep());
			properties.put("left_thresholds", thresholdResults.getLeftThresholds());
			properties.put("right_thresholds", thresholdResults.getRightThresholds());
			Filter.filterProperties(properties, thresholdResults.getKeep());
		}

		if (distance != null) {
			// Evaluate distance condition
			// this function is tested by the global find peaks test, granular testing on this level involves too much effort compared to it's worth
			double[] peakHeights = new double[peaks.length];
			for (int i = 0; i < peaks.length; i++) {
				peakHeights[i] = x[peaks[i]];
			}
			boolean[] keep = SelectByPeakDistance.call(peaks, peakHeights, distance);
			peaks = Filter.filterArray(peaks, keep);
			Filter.filterProperties(properties, keep);
		}

		if (prominence != null || width != null) {
			// Calculate prominence (required for both conditions)
			wlen = PeakProminences.argWlenAsExpected(wlen);
			PeakProminenceOutput prominences = PeakProminences.call(x, peaks, wlen);
			properties.put("prominences", prominences.getProminences());
			properties.put("left_bases", prominences.getLeftBases());
			properties.put("right_bases", prominences.getRightBases());
		}

		if (prominence != null) {
			// Evaluate prominence condition
			PairOfDoubleOrDArr pminmax = UnpackConditionArgs.call(prominence, x, peaks);
			boolean[] keep = SelectByProperty.call((double[]) properties.get("prominences"), pminmax.getFirst(), pminmax.getSecond());
			peaks = Filter.filterArray(peaks, keep);
			Filter.filterProperties(properties, keep);
		}

		if (width != null) {
			// Calculate widths
			PeakWidthsOutput widths = PeakWidth.call(x, peaks, relHeight, (double[])properties.get("prominences"),
					(int[]) properties.get("left_bases"), (int[]) properties.get("right_bases"));
			properties.put("widths", widths.getWidths());
			properties.put("width_heights", widths.getWidthHeights());
			properties.put("left_ips", widths.getLeftIps());
			properties.put("right_ips", widths.getRightIps());
			// Evaluate width condition
			PairOfDoubleOrDArr wminmax = UnpackConditionArgs.call(width, x, peaks);
			boolean[] keep = SelectByProperty.call((double[]) properties.get("widths"), wminmax.getFirst(), wminmax.getSecond());
			peaks = Filter.filterArray(peaks, keep);
			Filter.filterProperties(properties, keep);
		}

		return new FindPeaksOutput(peaks, properties);
	}

}
