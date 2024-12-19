package com.fa993.variations;

import com.fa993.types.LocalMaximaOutput;

/**
 * Unifying Interface for different LocalMaxima Implementations.
 *
 * This interface provides a standard method to detect local maxima in a 1D signal.
 * Implementing classes are expected to provide their specific algorithm for detecting
 * the indices of local maxima along with their left and right edges.
 */
public interface LocalMaxima {

	/**
	 * Detects local maxima in a 1D signal.
	 *
	 * @param x The input signal array.
	 * @return A {@link LocalMaximaOutput} object containing:
	 *         - Indices of detected local maxima (midpoints).
	 *         - Indices of the left and right edges for each peak.
	 * @throws IllegalArgumentException If the input signal is null or empty.
	 */
	 LocalMaximaOutput localMaxima1D(double[] x);
}