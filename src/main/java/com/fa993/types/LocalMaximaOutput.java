package com.fa993.types;

import java.util.Arrays;

public class LocalMaximaOutput {
	private int[] midpoints;
	private int[] leftEdges;
	private int[] rightEdges;

	public LocalMaximaOutput(int[] midpoints, int[] leftEdges, int[] rightEdges) {
		this.midpoints = midpoints;
		this.leftEdges = leftEdges;
		this.rightEdges = rightEdges;
	}

	public int[] getMidpoints() {
		return midpoints;
	}

	public void setMidpoints(int[] midpoints) {
		this.midpoints = midpoints;
	}

	public int[] getLeftEdges() {
		return leftEdges;
	}

	public void setLeftEdges(int[] leftEdges) {
		this.leftEdges = leftEdges;
	}

	public int[] getRightEdges() {
		return rightEdges;
	}

	public void setRightEdges(int[] rightEdges) {
		this.rightEdges = rightEdges;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LocalMaximaOutput that = (LocalMaximaOutput) o;
		return Arrays.equals(getMidpoints(), that.getMidpoints()) && Arrays.equals(getLeftEdges(), that.getLeftEdges()) && Arrays.equals(getRightEdges(), that.getRightEdges());
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(getMidpoints());
		result = 31 * result + Arrays.hashCode(getLeftEdges());
		result = 31 * result + Arrays.hashCode(getRightEdges());
		return result;
	}
}
