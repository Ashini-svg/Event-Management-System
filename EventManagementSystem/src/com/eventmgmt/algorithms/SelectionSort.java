package com.eventmgmt.algorithms;

public class SelectionSort {
	
	private static <T extends Comparable<T>> int compare(T a, T b) {
		if (a == null && b == null) return 0;
		if (a == null) return 1;
		if (b == null) return -1;
		return a.compareTo(b);
	}

    // Generic Selection Sort that works on Event, Attendee, and Venue
    public static <T extends Comparable<T>> void sort(T[] array) {
        // Stop early if array is empty or has only 1 item
        if (array == null || array.length <= 1) {
            return;
        }

        int n = array.length;

        // Loop through each element in the array
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;

            // Look through the remaining unsorted items to find the real minimum
            for (int j = i + 1; j < n; j++) {
                if (compare(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Swap smallest found item to the current index
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
    
    // Execution time measurement method for Task 3 benchmarking
    public static <T extends Comparable<T>> long sortWithTimer(T[] array) {
        long startTime = System.nanoTime(); // Record start time
        sort(array);                        // Perform sort
        long endTime = System.nanoTime();   // Record end time
        return (endTime - startTime);       // Return total duration in nanoseconds
    }
}
