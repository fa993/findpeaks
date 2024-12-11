package com.fa993.types;

import java.util.Arrays;

public class PeakProminenceOutput {

	private double[] prominences;
	int[] leftBases;
	int[] rightBases;

	public PeakProminenceOutput(double[] prominences, int[] leftBases, int[] rightBases) {
		this.prominences = prominences;
		this.leftBases = leftBases;
		this.rightBases = rightBases;
	}

	public double[] getProminences() {
		return prominences;
	}

	public void setProminences(double[] prominences) {
		this.prominences = prominences;
	}

	public int[] getLeftBases() {
		return leftBases;
	}

	public void setLeftBases(int[] leftBases) {
		this.leftBases = leftBases;
	}

	public int[] getRightBases() {
		return rightBases;
	}

	public void setRightBases(int[] rightBases) {
		this.rightBases = rightBases;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PeakProminenceOutput that = (PeakProminenceOutput) o;
		return Arrays.equals(getProminences(), that.getProminences()) && Arrays.equals(getLeftBases(), that.getLeftBases()) && Arrays.equals(getRightBases(), that.getRightBases());
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(getProminences());
		result = 31 * result + Arrays.hashCode(getLeftBases());
		result = 31 * result + Arrays.hashCode(getRightBases());
		return result;
	}
}
