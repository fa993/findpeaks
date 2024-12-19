package com.fa993.types;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the output of a peak-finding operation.
 * <p>
 * This class encapsulates the indices of the midpoints of peaks and additional properties
 * associated with the peaks.
 * </p>
 * <p>
 * Properties may include various metrics or attributes related to the identified peaks,
 * stored in a map structure with string keys and object values.
 * </p>
 */
public class FindPeaksOutput {
	/**
	 * An array containing the indices of the midpoints of detected peaks.
	 */
	private int[] midpoints;

	/**
	 * A map holding additional properties related to the detected peaks.
	 * The keys are strings describing the property, and the values are objects
	 * containing the corresponding data.
	 */
	private Map<String, Object> properties;

	/**
	 * Constructs a new {@code FindPeaksOutput} object with the specified midpoints and properties.
	 *
	 * @param midpoints  an array of integers representing the midpoints of the peaks
	 * @param properties a map containing additional properties of the peaks
	 */
	public FindPeaksOutput(int[] midpoints, Map<String, Object> properties) {
		this.midpoints = midpoints;
		this.properties = properties;
	}

	/**
	 * Returns the indices of the midpoints of detected peaks.
	 *
	 * @return an array of integers representing the midpoints
	 */
	public int[] getMidpoints() {
		return midpoints;
	}

	/**
	 * Returns the additional properties associated with the peaks.
	 *
	 * @return a map containing properties of the peaks
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * Compares this object to another object for equality.
	 * <p>
	 * Two {@code FindPeaksOutput} objects are considered equal if:
	 * <ul>
	 * <li>Both have the same midpoints</li>
	 * <li>Both have properties maps with the same keys and values</li>
	 * <li>Array values in the properties maps are compared deeply</li>
	 * </ul>
	 * </p>
	 * <p><b>Note:</b> Two empty arrays are considered equal regardless of their types.</p>
	 *
	 * @param o the object to compare to
	 * @return {@code true} if the objects are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FindPeaksOutput that = (FindPeaksOutput) o;
		// check maps differently
		if(this.getProperties() == null || that.getProperties() == null) {
			return false;
		}
		Map<String, Object> thisMap = this.getProperties();
		Map<String, Object> thatMap = that.getProperties();
		if(!thisMap.keySet().equals(thatMap.keySet())) {
			return false;
		}
		for(String key : thisMap.keySet()) {
			Object thisVal = thisMap.get(key);
			Object thatVal = thatMap.get(key);
			if(thisVal.getClass().isArray() && thatVal.getClass().isArray()) {
				if(Array.getLength(thisVal) == 0 && Array.getLength(thatVal) == 0) {
					continue;
				} else if(!Objects.deepEquals(thisVal, thatVal)){
					return false;
				} else {
					continue;
				}
			} else {
				if(!Objects.deepEquals(thisVal, thatVal)) {
					return false;
				} else {
					continue;
				}
			}
		}
		return Arrays.equals(getMidpoints(), that.getMidpoints());
	}

	/**
	 * Computes the hash code for this {@code FindPeaksOutput} object.
	 * <p>
	 * The hash code is computed using the midpoints array and the properties map.
	 * </p>
	 *
	 * @return the hash code value
	 */
	@Override
	public int hashCode() {
		int result = Objects.hash(getProperties());
		result = 31 * result + Arrays.hashCode(getMidpoints());
		return result;
	}

	/**
	 * Returns a string representation of this {@code FindPeaksOutput} object.
	 *
	 * @return a string representation of the object, including the midpoints array and properties map
	 */
	@Override
	public String toString() {
		return "FindPeaksOutput{" +
				"midpoints=" + Arrays.toString(midpoints) +
				", properties=" + properties +
				'}';
	}


}
