package com.fa993.function.supertype;

public class NumOrTwoSeqOrNdArr extends Either.OfThree<Double, double[], double[][]>{


	protected NumOrTwoSeqOrNdArr(Double first, double[] second, double[][] third, Either.Selector selector) {
		super(first, second, third, selector);
	}

	public static NumOrTwoSeqOrNdArr first(Double first) {
		return new NumOrTwoSeqOrNdArr(first, null, null, Either.Selector.First);
	}

	public static NumOrTwoSeqOrNdArr second(double[] second) {
		return new NumOrTwoSeqOrNdArr(null, second, null, Either.Selector.Second);
	}

	public static NumOrTwoSeqOrNdArr third(double[][] third) {
		return new NumOrTwoSeqOrNdArr(null, null, third, Either.Selector.Third);
	}
}