package com.fa993.function.supertype;

public class NumOrTwoSeqOrNdArr extends Either.OfThree<Double, double[], double[][]>{


	protected NumOrTwoSeqOrNdArr(Double first, double[] second, double[][] third, Either.Selector selector) {
		super(first, second, third, selector);
	}
}