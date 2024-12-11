package com.fa993.variations;

import com.fa993.types.LocalMaximaOutput;

/**
 * Unifying Interface for different LocalMaxima Implementations
 */
public interface LocalMaxima {
	 LocalMaximaOutput localMaxima1D(double[] x);
}