package com.fa993.types;

public class SelectThresholdOutput {
	private boolean[] keep;
	private double[] leftThresholds;
	private double[] rightThresholds;

	public SelectThresholdOutput(boolean[] keep, double[] leftThresholds, double[] rightThresholds) {
		this.keep = keep;
		this.leftThresholds = leftThresholds;
		this.rightThresholds = rightThresholds;
	}

	public boolean[] getKeep() {
		return keep;
	}

	public void setKeep(boolean[] keep) {
		this.keep = keep;
	}

	public double[] getLeftThresholds() {
		return leftThresholds;
	}

	public void setLeftThresholds(double[] leftThresholds) {
		this.leftThresholds = leftThresholds;
	}

	public double[] getRightThresholds() {
		return rightThresholds;
	}

	public void setRightThresholds(double[] rightThresholds) {
		this.rightThresholds = rightThresholds;
	}
}
