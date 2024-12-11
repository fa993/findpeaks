package com.fa993.function.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Filter {

	public static int[] filterArray(int[] peaks, boolean[] mask) {
		List<Integer> lst = new ArrayList<>();
		for (int i = 0; i < peaks.length; i++) {
			if(mask[i]) {
				lst.add(peaks[i]);
			}
		}
		return lst.stream().mapToInt(i -> i).toArray();
	}

	public static double[] filterArray(double[] peaks, boolean[] mask) {
		List<Double> lst = new ArrayList<>();
		for (int i = 0; i < peaks.length; i++) {
			if(mask[i]) {
				lst.add(peaks[i]);
			}
		}
		return lst.stream().mapToDouble(i -> i).toArray();
	}

	public static void filterProperties(Map<String, Object> props, boolean[] mask) {
		for(String key: props.keySet()) {
			Object vals = props.get(key);
			if(vals instanceof int[]) {
				props.put(key, filterArray((int[]) vals, mask));
			} else if(vals instanceof double[]) {
				props.put(key, filterArray((double[]) vals, mask));
			} else {
				// DO NOTHING
			}
		}
	}


}
