package com.fa993.types;

import java.util.Arrays;

/**
 * Represents the output of a peak prominence calculation.
 * <p>
 * This class encapsulates the prominence values for peaks, along with their
 * corresponding left and right bases.
 * </p>
 */
public class PeakProminenceOutput {

	/**
	 * An array containing the prominence values of detected peaks.
	 */
	private double[] prominences;

	/**
	 * An array containing the indices of the left bases of the detected peaks.
	 */
	int[] leftBases;

	/**
	 * An array containing the indices of the right bases of the detected peaks.
	 */
	int[] rightBases;

	/**
	 * Constructs a new {@code PeakProminenceOutput} object with the specified prominences,
	 * left bases, and right bases.
	 *
	 * @param prominences an array of doubles representing the prominence values of the peaks
	 * @param leftBases   an array of integers representing the left bases of the peaks
	 * @param rightBases  an array of integers representing the right bases of the peaks
	 */
	public PeakProminenceOutput(double[] prominences, int[] leftBases, int[] rightBases) {
		this.prominences = prominences;
		this.leftBases = leftBases;
		this.rightBases = rightBases;
	}

	/**
	 * Returns the prominence values of the detected peaks.
	 *
	 * @return an array of doubles representing the prominence values
	 */
	public double[] getProminences() {
		return prominences;
	}

	/**
	 * Returns the indices of the left bases of the detected peaks.
	 *
	 * @return an array of integers representing the left bases
	 */
	public int[] getLeftBases() {
		return leftBases;
	}

	/**
	 * Returns the indices of the right bases of the detected peaks.
	 *
	 * @return an array of integers representing the right bases
	 */
	public int[] getRightBases() {
		return rightBases;
	}

	/**
	 * Compares this object to another object for equality.
	 * <p>
	 * Two {@code PeakProminenceOutput} objects are considered equal if their prominences,
	 * left bases, and right bases arrays are all equal.
	 * </p>
	 *
	 * @param o the object to compare to
	 * @return {@code true} if the objects are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PeakProminenceOutput that = (PeakProminenceOutput) o;
		return Arrays.equals(getProminences(), that.getProminences()) && Arrays.equals(getLeftBases(), that.getLeftBases()) && Arrays.equals(getRightBases(), that.getRightBases());
	}

	/**
	 * Computes the hash code for this {@code PeakProminenceOutput} object.
	 * <p>
	 * The hash code is computed based on the prominences, left bases, and right bases arrays.
	 * </p>
	 *
	 * @return the hash code value
	 */
	@Override
	public int hashCode() {
		int result = Arrays.hashCode(getProminences());
		result = 31 * result + Arrays.hashCode(getLeftBases());
		result = 31 * result + Arrays.hashCode(getRightBases());
		return result;
	}
}
