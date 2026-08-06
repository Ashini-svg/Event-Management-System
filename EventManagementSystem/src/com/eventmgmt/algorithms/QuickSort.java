package com.eventmgmt.algorithms;

import com.eventmgmt.model.Attendee;

public class QuickSort {

    public static void sort(Attendee[] attendees, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(attendees, low, high);

            // Recursively sort elements before and after partition
            sort(attendees, low, pivotIndex - 1);
            sort(attendees, pivotIndex + 1, high);
        }
    }

    private static int partition(Attendee[] attendees, int low, int high) {
        // Selecting the last element as the pivot
        Attendee pivot = attendees[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            // Compare attendee IDs in ascending order
            if (attendees[j].getAttendeeId().compareToIgnoreCase(pivot.getAttendeeId()) <= 0) {
                i++;
                // Swap attendees[i] and attendees[j]
                Attendee temp = attendees[i];
                attendees[i] = attendees[j];
                attendees[j] = temp;
            }
        }

        // Swap attendees[i + 1] and pivot (attendees[high])
        Attendee temp = attendees[i + 1];
        attendees[i + 1] = attendees[high];
        attendees[high] = temp;

        return i + 1;
    }
}