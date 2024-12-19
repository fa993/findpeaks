package com.fa993.types;

import java.util.Arrays;

/**
 * Represents the output of peak width calculations.
 * <p>
 * This class encapsulates the widths of peaks, the corresponding heights at which the widths are measured,
 * and the left and right intersection points (IPs) of the widths on the curve.
 * </p>
 */
public class PeakWidthsOutput {

	/**
	 * An array containing the widths of the detected peaks.
	 */
	private double[] widths;

	/**
	 * An array containing the heights at which the widths of the peaks are measured.
	 */
	private double[] widthHeights;

	/**
	 * An array containing the left intersection points (IPs) of the widths on the curve.
	 */
	private double[] leftIps;

	/**
	 * An array containing the right intersection points (IPs) of the widths on the curve.
	 */
	private double[] rightIps;

	/**
	 * Constructs a new {@code PeakWidthsOutput} object with the specified widths, width heights,
	 * left intersection points, and right intersection points.
	 *
	 * @param widths       an array of doubles representing the widths of the peaks
	 * @param widthHeights an array of doubles representing the heights at which the widths are measured
	 * @param leftIps      an array of doubles representing the left intersection points of the widths
	 * @param rightIps     an array of doubles representing the right intersection points of the widths
	 */
	public PeakWidthsOutput(double[] widths, double[] widthHeights, double[] leftIps, double[] rightIps) {
		this.widths = widths;
		this.widthHeights = widthHeights;
		this.leftIps = leftIps;
		this.rightIps = rightIps;
	}

	/**
	 * Gets the array of widths of the detected peaks.
	 *
	 * @return an array of doubles representing the widths of the peaks
	 */
	public double[] getWidths() {
		return widths;
	}

	/**
	 * Gets the array of heights at which the widths of the peaks are measured.
	 *
	 * @return an array of doubles representing the heights at which the widths are measured
	 */
	public double[] getWidthHeights() {
		return widthHeights;
	}

	/**
	 * Gets the array of left intersection points (IPs) of the widths on the curve.
	 *
	 * @return an array of doubles representing the left intersection points of the widths
	 */
	public double[] getLeftIps() {
		return leftIps;
	}

	/**
	 * Gets the array of right intersection points (IPs) of the widths on the curve.
	 *
	 * @return an array of doubles representing the right intersection points of the widths
	 */
	public double[] getRightIps() {
		return rightIps;
	}

	/**
	 * Compares this object to another object for equality.
	 * <p>
	 * Two {@code PeakWidthsOutput} objects are considered equal if their widths, width heights,
	 * left intersection points, and right intersection points arrays are all equal.
	 * </p>
	 *
	 * @param o the object to compare to
	 * @return {@code true} if the objects are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PeakWidthsOutput that = (PeakWidthsOutput) o;
		return Arrays.equals(getWidths(), that.getWidths()) && Arrays.equals(getWidthHeights(), that.getWidthHeights()) && Arrays.equals(getLeftIps(), that.getLeftIps()) && Arrays.equals(getRightIps(), that.getRightIps());
	}

	/**
	 * Computes the hash code for this {@code PeakWidthsOutput} object.
	 * <p>
	 * The hash code is computed based on the widths, width heights, left intersection points,
	 * and right intersection points arrays.
	 * </p>
	 *
	 * @return the hash code value
	 */
	@Override
	public int hashCode() {
		int result = Arrays.hashCode(getWidths());
		result = 31 * result + Arrays.hashCode(getWidthHeights());
		result = 31 * result + Arrays.hashCode(getLeftIps());
		result = 31 * result + Arrays.hashCode(getRightIps());
		return result;
	}
}
