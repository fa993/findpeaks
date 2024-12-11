package com.fa993.function.supertype;

import lombok.NonNull;

public class NumOrTwoSeqOrNdArr extends Either.OfThree<Double, double[], double[][]>{

	protected NumOrTwoSeqOrNdArr(Double first, double[] second, double[][] third, Either.Selector selector) {
		super(first, second, third, selector);
	}

	public static NumOrTwoSeqOrNdArr first(@NonNull Double first) {
		return new NumOrTwoSeqOrNdArr(first, null, null, Either.Selector.First);
	}

	public static NumOrTwoSeqOrNdArr second(@NonNull double[] second) {
		if(second.length != 2) {
			throw new IllegalArgumentException("Sequence must be of length 2");
		}
		return new NumOrTwoSeqOrNdArr(null, second, null, Either.Selector.Second);
	}

	public static NumOrTwoSeqOrNdArr third(@NonNull double[][] third) {
		return new NumOrTwoSeqOrNdArr(null, null, third, Either.Selector.Third);
	}
}