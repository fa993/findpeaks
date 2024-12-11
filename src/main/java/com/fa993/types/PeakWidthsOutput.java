package com.fa993.types;

import java.util.Arrays;

public class PeakWidthsOutput {
	private double[] widths;
	private double[] widthHeights;
	private double[] leftIps;
	private double[] rightIps;

	public PeakWidthsOutput(double[] widths, double[] widthHeights, double[] leftIps, double[] rightIps) {
		this.widths = widths;
		this.widthHeights = widthHeights;
		this.leftIps = leftIps;
		this.rightIps = rightIps;
	}

	public double[] getWidths() {
		return widths;
	}

	public void setWidths(double[] widths) {
		this.widths = widths;
	}

	public double[] getWidthHeights() {
		return widthHeights;
	}

	public void setWidthHeights(double[] widthHeights) {
		this.widthHeights = widthHeights;
	}

	public double[] getLeftIps() {
		return leftIps;
	}

	public void setLeftIps(double[] leftIps) {
		this.leftIps = leftIps;
	}

	public double[] getRightIps() {
		return rightIps;
	}

	public void setRightIps(double[] rightIps) {
		this.rightIps = rightIps;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PeakWidthsOutput that = (PeakWidthsOutput) o;
		return Arrays.equals(getWidths(), that.getWidths()) && Arrays.equals(getWidthHeights(), that.getWidthHeights()) && Arrays.equals(getLeftIps(), that.getLeftIps()) && Arrays.equals(getRightIps(), that.getRightIps());
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(getWidths());
		result = 31 * result + Arrays.hashCode(getWidthHeights());
		result = 31 * result + Arrays.hashCode(getLeftIps());
		result = 31 * result + Arrays.hashCode(getRightIps());
		return result;
	}
}
