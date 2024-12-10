package com.fa993.function.supertype;

public class Pair<P, Q> {
	private P first;
	private Q second;

	public Pair(P first, Q second) {
		this.first = first;
		this.second = second;
	}

	public P getFirst() {
		return first;
	}

	public Q getSecond() {
		return second;
	}
}
