package com.eventmgmt.algorithms;

import java.util.List;

public class InsertionSort {
	
	private static <T extends Comparable<T>> int compare(T a, T b) {
		if (a == null && b == null) return 0;
		if (a == null) return 1;
		if (b == null) return -1;
		return a.compareTo(b);
	}
	
	// Sorts a List in-place
	public static <T extends Comparable<T>> void sort(List<T> list) {
		if (list == null || list.size() <= 1) {
			return;
		}
		
		for (int i = 1; i < list.size(); i++) {
			T key = list.get(i);  // current element to be placed in the sorted portion
			int j = i - 1;  // index of the last element in the sorted portion
			
			while (j >= 0 && compare(list.get(j), key) > 0) {
				list.set(j + 1, list.get(j));  // shift element right
				j--;  // move back to compare previous element
			}
			list.set(j + 1, key);
		}
	}
	
	public static <T extends Comparable<T>> void sort(T[] array) {
		if (array == null || array.length <= 1) {
			return;
		}
		
		for (int i = 1; i < array.length; i++) {
			T key = array[i];  // current element to insert
			int j = i - 1;  // end of sorted sub-array
			
			while (j >= 0 && compare(array[j], key) > 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;  // Place key in correct spot
		}
	}
}
