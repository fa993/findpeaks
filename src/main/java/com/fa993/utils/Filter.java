package com.fa993.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class for filtering arrays and properties based on a boolean mask.
 *
 * The `Filter` class provides methods to filter integer and double arrays, as well as
 * map properties containing arrays, based on a given boolean mask.
 */
public class Filter {

	/**
	 * Filters an integer array based on a boolean mask.
	 *
	 * This method creates a new array containing only the elements from `peaks`
	 * where the corresponding index in `mask` is `true`.
	 *
	 * @param peaks The input integer array to filter.
	 * @param mask  The boolean mask array. Must have the same length as `peaks`.
	 * @return A new integer array containing the filtered values.
	 * @throws IllegalArgumentException if `peaks` and `mask` have different lengths.
	 */
	public static int[] filterArray(int[] peaks, boolean[] mask) {
		List<Integer> lst = new ArrayList<>();
		for (int i = 0; i < peaks.length; i++) {
			if(mask[i]) {
				lst.add(peaks[i]);
			}
		}
		return lst.stream().mapToInt(i -> i).toArray();
	}

	/**
	 * Filters a double array based on a boolean mask.
	 *
	 * This method creates a new array containing only the elements from `peaks`
	 * where the corresponding index in `mask` is `true`.
	 *
	 * @param peaks The input double array to filter.
	 * @param mask  The boolean mask array. Must have the same length as `peaks`.
	 * @return A new double array containing the filtered values.
	 * @throws IllegalArgumentException if `peaks` and `mask` have different lengths.
	 */
	public static double[] filterArray(double[] peaks, boolean[] mask) {
		List<Double> lst = new ArrayList<>();
		for (int i = 0; i < peaks.length; i++) {
			if(mask[i]) {
				lst.add(peaks[i]);
			}
		}
		return lst.stream().mapToDouble(i -> i).toArray();
	}

	/**
	 * Filters properties in a map based on a boolean mask.
	 *
	 * This method updates the values of the map for keys that have arrays as values,
	 * applying the filtering operation only to arrays of type `int[]` or `double[]`.
	 * Other value types are ignored.
	 *
	 * @param props A map of properties where keys are strings and values are arrays.
	 *              Only arrays of type `int[]` or `double[]` are processed.
	 * @param mask  The boolean mask array. Must have the same length as the arrays being filtered.
	 * @throws IllegalArgumentException if any array in the map has a different length than the `mask`.
	 */
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
