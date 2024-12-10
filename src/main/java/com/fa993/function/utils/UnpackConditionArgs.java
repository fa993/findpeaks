package com.fa993.function.utils;

import com.fa993.function.supertype.Either;
import com.fa993.function.supertype.NumOrTwoSeqOrNdArr;
import com.fa993.function.supertype.PairOfDoubleOrDArr;

public class UnpackConditionArgs {

	public static PairOfDoubleOrDArr call(NumOrTwoSeqOrNdArr interval, double[] x, int[] peaks) {
		Either.OfTwo<Double, double[]> imin = null, imax = null;

		if (interval.getSelector().isFirst()) {
			imin = Either.OfTwo.first(interval.getFirst());
		} else if(interval.getSelector().isSecond()) {
			imin = Either.OfTwo.first(interval.getSecond()[0]);
			imax = Either.OfTwo.first(interval.getSecond()[1]);
		} else if(interval.getSelector().isThird()) {
			imin = Either.OfTwo.second(interval.getThird()[0]);
			imax = Either.OfTwo.second(interval.getThird()[1]);
		} else {
			throw new RuntimeException("Unknown Selector for Either");
		}

		// Reduce arrays if necessary
		if (imin.getSelector().isSecond()) {
			double[] iminArray = imin.getSecond();
			if (iminArray.length != x.length) {
				throw new IllegalArgumentException("Array size of lower interval border must match x.");
			}
			imin = Either.OfTwo.second(extractValuesAtIndices(iminArray, peaks));
		}
		if (imax != null && imax.getSelector().isSecond()) {
			double[] imaxArray = imax.getSecond();
			if (imaxArray.length != x.length) {
				throw new IllegalArgumentException("Array size of upper interval border must match x.");
			}
			imax = Either.OfTwo.second(extractValuesAtIndices(imaxArray, peaks));
		}

		return new PairOfDoubleOrDArr(imin, imax);
	}

	private static double[] extractValuesAtIndices(double[] array, int[] indices) {
		double[] reducedArray = new double[indices.length];
		for (int i = 0; i < indices.length; i++) {
			reducedArray[i] = array[indices[i]];
		}
		return reducedArray;
	}

}
