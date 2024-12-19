package com.fa993.types;

import java.util.Arrays;

/**
 * Represents the output of a local maxima detection operation.
 * <p>
 * This class encapsulates the indices of the midpoints of local maxima, along with
 * their corresponding left and right edges.
 * </p>
 */
public class LocalMaximaOutput {
	/**
	 * An array containing the indices of the midpoints of detected local maxima.
	 */
	private int[] midpoints;
	/**
	 * An array containing the indices of the left edges of the detected local maxima.
	 */
	private int[] leftEdges;
	/**
	 * An array containing the indices of the right edges of the detected local maxima.
	 */
	private int[] rightEdges;

	/**
	 * Constructs a new {@code LocalMaximaOutput} object with the specified midpoints,
	 * left edges, and right edges.
	 *
	 * @param midpoints  an array of integers representing the midpoints of the local maxima
	 * @param leftEdges  an array of integers representing the left edges of the local maxima
	 * @param rightEdges an array of integers representing the right edges of the local maxima
	 */
	public LocalMaximaOutput(int[] midpoints, int[] leftEdges, int[] rightEdges) {
		this.midpoints = midpoints;
		this.leftEdges = leftEdges;
		this.rightEdges = rightEdges;
	}

	/**
	 * Returns the midpoints of the detected local maxima.
	 *
	 * @return an array of integers representing the midpoints
	 */
	public int[] getMidpoints() {
		return midpoints;
	}

	/**
	 * Returns the left edges of the detected local maxima.
	 *
	 * @return an array of integers representing the left edges
	 */
	public int[] getLeftEdges() {
		return leftEdges;
	}

	/**
	 * Returns the right edges of the detected local maxima.
	 *
	 * @return an array of integers representing the right edges
	 */
	public int[] getRightEdges() {
		return rightEdges;
	}

	/**
	 * Compares this object to another object for equality.
	 * <p>
	 * Two {@code LocalMaximaOutput} objects are considered equal if their midpoints,
	 * left edges, and right edges arrays are all equal.
	 * </p>
	 *
	 * @param o the object to compare to
	 * @return {@code true} if the objects are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LocalMaximaOutput that = (LocalMaximaOutput) o;
		return Arrays.equals(getMidpoints(), that.getMidpoints()) && Arrays.equals(getLeftEdges(), that.getLeftEdges()) && Arrays.equals(getRightEdges(), that.getRightEdges());
	}

	/**
	 * Computes the hash code for this {@code LocalMaximaOutput} object.
	 * <p>
	 * The hash code is computed based on the midpoints, left edges, and right edges arrays.
	 * </p>
	 *
	 * @return the hash code value
	 */
	@Override
	public int hashCode() {
		int result = Arrays.hashCode(getMidpoints());
		result = 31 * result + Arrays.hashCode(getLeftEdges());
		result = 31 * result + Arrays.hashCode(getRightEdges());
		return result;
	}
}
