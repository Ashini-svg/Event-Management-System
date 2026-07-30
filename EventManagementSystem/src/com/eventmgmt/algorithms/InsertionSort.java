package com.eventmgmt.algorithms;

import java.util.List;

public class InsertionSort {
	
	// Sorts a List in-place
	public static <T extends Comparable<T>> void sort(List<T> list) {
		// If the list is empty or has only one element, it is already sorted
		if (list == null || list.size() <= 1) {
			return;
		}
		// Loop through the list starting from the second element
		for (int i = 1; i < list.size(); i++) {
			T key = list.get(i);  // current element to be placed in the sorted portion
			int j = i - 1;  // index of the last element in the sorted portion
			
			// Shift elements that are greater than key to one position ahead
			while (j >= 0 && list.get(j).compareTo(key) > 0) {
				list.set(j + 1, list.get(j));  // shift element right
				j--;  // move back to compare previous element
			}
			
			// insert the key into its correct sorted position
			list.set(j + 1, key);
		}
	}
	
	// Overloaded method to sort standard Java arrays in-place
	public static <T extends Comparable<T>> void sort(T[] array) {
		// If array is empty or has one element, no sorting needed
		if (array == null || array.length <= 1) {
			return;
		}
		
		// Start from second element
		for (int i = 1; i < array.length; i++) {
			T key = array[i];  // current element to insert
			int j = i - 1;  // end of sorted sub-array
			
			while (j >= 0 && array[j].compareTo(key) > 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;  // Place key in correct spot
		}
	}
}
