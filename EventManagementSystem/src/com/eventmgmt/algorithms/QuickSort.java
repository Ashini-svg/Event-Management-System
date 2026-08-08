package com.eventmgmt.algorithms;

import com.eventmgmt.model.Attendee;

public class QuickSort {

    public static void sort(Attendee[] attendees, int low, int high) {
        // Validation check for null array, empty bounds, or invalid range
        if (attendees == null || attendees.length <= 1 || low < 0 || high >= attendees.length || low >= high) {
            return;
        }
        
        // Move any null entries in low-high range to the end of this partition
        high = trimNullsToRight(attendees, low, high);
        if (low >= high) {
        	return;
        }

        int pivotIndex = partition(attendees, low, high);

        // Recursively sort elements before and after partition
        sort(attendees, low, pivotIndex - 1);
        sort(attendees, pivotIndex + 1, high);
    }
    
    private static int trimNullsToRight(Attendee[] attendees, int low, int high) {
    	while (high >= low && attendees[high] == null) {
    		high--;
    	}
    	for (int i = low; i < high; i++) {
    		if (attendees[i] == null) {
    			Attendee temp = attendees[i];
    			attendees[i] = attendees[i];
    			attendees[high] = temp;
    			
    			while (high >= low && attendees[high] == null) {
    				high--;
    			}
    		}
    	}
    	return high;
    }

    private static int partition(Attendee[] attendees, int low, int high) {
        Attendee pivot = attendees[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(attendees[j], pivot) <= 0) {
                i++;
                Attendee temp = attendees[i];
                attendees[i] = attendees[j];
                attendees[j] = temp;
            }
        }

        Attendee temp = attendees[i + 1];
        attendees[i + 1] = attendees[high];
        attendees[high] = temp;

        return i + 1;
    }

    // Places null elements or null IDs at the end of the array
    private static int compare(Attendee a1, Attendee a2) {
        if (a1 == null && a2 == null) return 0;
        if (a1 == null) return 1;
        if (a2 == null) return -1;

        String id1 = (a1.getAttendeeId() != null) ? a1.getAttendeeId() : "";
        String id2 = (a2.getAttendeeId() != null) ? a2.getAttendeeId() : "";

        return id1.compareToIgnoreCase(id2);
    }
}