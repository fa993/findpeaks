package com.fa993.function;

import lombok.EqualsAndHashCode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * POJO for describing output of find peaks
 */
public class FindPeaksOutput {
	// holds midpoints of peaks - indices
	private int[] midpoints;
	// other properties about peaks
	private Map<String, Object> properties;

	public FindPeaksOutput(int[] midpoints, Map<String, Object> properties) {
		this.midpoints = midpoints;
		this.properties = properties;
	}

	public int[] getMidpoints() {
		return midpoints;
	}

	public void setMidpoints(int[] midpoints) {
		this.midpoints = midpoints;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

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


//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		FindPeaksOutput that = (FindPeaksOutput) o;
//		return Arrays.equals(getMidpoints(), that.getMidpoints()) && Objects.deepEquals(getProperties(), that.getProperties());
//	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getProperties());
		result = 31 * result + Arrays.hashCode(getMidpoints());
		return result;
	}

	@Override
	public String toString() {
		return "FindPeaksOutput{" +
				"midpoints=" + Arrays.toString(midpoints) +
				", properties=" + properties +
				'}';
	}


}
