package com.fa993.function;

import java.util.Map;

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
}
