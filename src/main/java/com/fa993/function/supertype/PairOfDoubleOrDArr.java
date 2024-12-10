package com.fa993.function.supertype;

public class PairOfDoubleOrDArr extends Pair<Either.OfTwo<Double, double[]>, Either.OfTwo<Double, double[]>>{
	public PairOfDoubleOrDArr(Either.OfTwo<Double, double[]> first, Either.OfTwo<Double, double[]> second) {
		super(first, second);
	}
}
