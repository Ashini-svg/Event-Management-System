package com.eventmgmt.algorithms;

import com.eventmgmt.model.Venue;

public class MergeSort {
	
	
	// Sorts an array of Venue objects using Merge Sort
	public void sort(Venue[] venues) {

	    if (venues == null || venues.length <= 1) {
	        return;
	    }

	    mergeSort(venues, 0, venues.length - 1);
	}
	
	
	// Recursively divides the array into smaller halves
	private void mergeSort(Venue[] venues, int left, int right) {

	    if (left < right) {

	        int middle = (left + right) / 2;

	        mergeSort(venues, left, middle);
	        mergeSort(venues, middle + 1, right);

	        merge(venues, left, middle, right);
	    }
	}
	
	
	// Merges two sorted halves into one sorted array
	private void merge(Venue[] venues, int left, int middle, int right) {

	    int leftSize = middle - left + 1;
	    int rightSize = right - middle;

	    Venue[] leftArray = new Venue[leftSize];
	    Venue[] rightArray = new Venue[rightSize];

	    for (int i = 0; i < leftSize; i++) {
	        leftArray[i] = venues[left + i];
	    }

	    for (int j = 0; j < rightSize; j++) {
	        rightArray[j] = venues[middle + 1 + j];
	    }

	    int i = 0;
	    int j = 0;
	    int k = left;

	    while (i < leftSize && j < rightSize) {

	        if (leftArray[i].compareTo(rightArray[j]) <= 0) {
	            venues[k] = leftArray[i];
	            i++;
	        } else {
	            venues[k] = rightArray[j];
	            j++;
	        }

	        k++;
	    }

	    while (i < leftSize) {
	        venues[k] = leftArray[i];
	        i++;
	        k++;
	    }

	    while (j < rightSize) {
	        venues[k] = rightArray[j];
	        j++;
	        k++;
	    }
	}

}
