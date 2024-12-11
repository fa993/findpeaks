package com.fa993;

import com.fa993.types.FindPeaksOutput;
import com.fa993.types.LocalMaximaOutput;
import com.fa993.types.PeakProminenceOutput;
import com.fa993.types.SelectThresholdOutput;
import com.fa993.types.supertype.NumOrTwoSeqOrNdArr;
import com.fa993.types.supertype.PairOfDoubleOrDArr;
import com.fa993.utils.*;
import com.fa993.variations.LocalMaxima;
import com.fa993.variations.LocalMaximaJIU;

import java.util.HashMap;
import java.util.Map;

public class FindPeaks {
	// using JavaInUse's local maxima impl by default
	private LocalMaxima lm = new LocalMaximaJIU();

	public FindPeaks() {}

	public FindPeaks(LocalMaxima impl) {
		this.lm = impl;
	}


	/**
	 *
	 * @param points the points to find peaks for
	 * @return object with midpoints of peak, python 1-1
	 */
	public FindPeaksOutput call(double[] points) {
		return new FindPeaksOutput(lm.localMaxima1D(points).getMidpoints(), new HashMap<>());
	}

	// for this function both java in use and code convert give very similar output
	/**
	 * see python docs for complete explanation
	 * @return object with midpoints of peak, python 1-1
	 *
	 * @see <a href="https://github.com/scipy/scipy/blob/92d2a8592782ee19a1161d0bf3fc2241ba78bb63/scipy/signal/_peak_finding.py#L729">FindPeaks Source</a>
	 */
	public FindPeaksOutput call(double[] x, NumOrTwoSeqOrNdArr height, NumOrTwoSeqOrNdArr threshold, Double distance,
									   Double prominence, Double width, Integer wlen, Double relHeight,
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
			PairOfDoubleOrDArr pminmax = UnpackConditionArgs.call(NumOrTwoSeqOrNdArr.first(prominence), x, peaks);
			boolean[] keep = SelectByProperty.call((double[]) properties.get("prominences"), pminmax.getFirst(), pminmax.getSecond());
			peaks = Filter.filterArray(peaks, keep);
			Filter.filterProperties(properties, keep);
		}
//
//		if (width != null) {
//			// Calculate widths
//			double[][] widths = peakWidths(x, peaks, relHeight, properties.get("prominences"),
//					properties.get("left_bases"), properties.get("right_bases"));
//			properties.put("widths", widths[0]);
//			properties.put("width_heights", widths[1]);
//			properties.put("left_ips", widths[2]);
//			properties.put("right_ips", widths[3]);
//			// Evaluate width condition
//			double[] wminmax = UnpackConditionArgs.call(width, x, peaks);
//			int[] keep = selectByProperty(properties.get("widths"), wminmax[0], wminmax[1]);
//			peaks = filterArray(peaks, keep);
//			properties = filterProperties(properties, keep);
//		}

		return new FindPeaksOutput(peaks, properties);
	}

}
