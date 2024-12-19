package com.fa993.types.supertype;

public class NumOrTwoSeqOrNdArr extends Either.OfThree<Double, double[], double[][]>{

	protected NumOrTwoSeqOrNdArr(Double first, double[] second, double[][] third, Either.Selector selector) {
		super(first, second, third, selector);
	}

	public static NumOrTwoSeqOrNdArr first(Double first) {
		if (first == null) {
			throw new NullPointerException("first is marked non-null but is null");
		}
		return new NumOrTwoSeqOrNdArr(first, null, null, Either.Selector.First);
	}

	public static NumOrTwoSeqOrNdArr second(double[] second) {
		if (second == null) {
			throw new NullPointerException("second is marked non-null but is null");
		}
		if(second.length != 2) {
			throw new IllegalArgumentException("Sequence must be of length 2");
		}
		return new NumOrTwoSeqOrNdArr(null, second, null, Either.Selector.Second);
	}

	public static NumOrTwoSeqOrNdArr third(double[][] third) {
		if (third == null) {
			throw new NullPointerException("third is marked non-null but is null");
		}
		return new NumOrTwoSeqOrNdArr(null, null, third, Either.Selector.Third);
	}
}