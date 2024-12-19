package com.fa993.types;

/**
 * Represents the output of a threshold selection process.
 * <p>
 * This class encapsulates the results of a threshold-based filtering operation, including
 * which elements are retained and the associated left and right thresholds for each element.
 * </p>
 */
public class SelectThresholdOutput {

	/**
	 * A boolean array indicating whether each element meets the selection criteria.
	 * {@code true} indicates the element is retained, and {@code false} indicates it is not.
	 */
	private boolean[] keep;

	/**
	 * An array of doubles representing the left thresholds associated with the elements.
	 */
	private double[] leftThresholds;

	/**
	 * An array of doubles representing the right thresholds associated with the elements.
	 */
	private double[] rightThresholds;

	/**
	 * Constructs a new {@code SelectThresholdOutput} object with the specified keep array,
	 * left thresholds, and right thresholds.
	 *
	 * @param keep           a boolean array indicating which elements are retained
	 * @param leftThresholds an array of doubles representing the left thresholds
	 * @param rightThresholds an array of doubles representing the right thresholds
	 */
	public SelectThresholdOutput(boolean[] keep, double[] leftThresholds, double[] rightThresholds) {
		this.keep = keep;
		this.leftThresholds = leftThresholds;
		this.rightThresholds = rightThresholds;
	}

	/**
	 * Gets the boolean array indicating which elements are retained.
	 *
	 * @return a boolean array where {@code true} indicates the element is retained
	 * and {@code false} indicates it is not
	 */
	public boolean[] getKeep() {
		return keep;
	}

	/**
	 * Gets the array of left thresholds associated with the elements.
	 *
	 * @return an array of doubles representing the left thresholds
	 */
	public double[] getLeftThresholds() {
		return leftThresholds;
	}

	/**
	 * Gets the array of right thresholds associated with the elements.
	 *
	 * @return an array of doubles representing the right thresholds
	 */
	public double[] getRightThresholds() {
		return rightThresholds;
	}
}
