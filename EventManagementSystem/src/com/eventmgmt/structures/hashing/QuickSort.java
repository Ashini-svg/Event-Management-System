package com.eventmgmt.structures.hashing;

import com.eventmgmt.model.Attendee;

public class QuickSort {

    // Main QuickSort function for Attendee Array (Sorting by ID)
    public static void sortAttendeesById(Attendee[] attendees, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(attendees, low, high);

            // Recursive calls
            sortAttendeesById(attendees, low, pivotIndex - 1);
            sortAttendeesById(attendees, pivotIndex + 1, high);
        }
    }

    // Partition logic using Last Element as Pivot
    private static int partition(Attendee[] attendees, int low, int high) {
        Attendee pivot = attendees[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            // Null check and string comparison
            if (attendees[j] != null && pivot != null) {
                if (attendees[j].getAttendeeId().compareToIgnoreCase(pivot.getAttendeeId()) <= 0) {
                    i++;
                    // Swap attendees[i] and attendees[j]
                    Attendee temp = attendees[i];
                    attendees[i] = attendees[j];
                    attendees[j] = temp;
                }
            }
        }

        // Swap attendees[i+1] and pivot (attendees[high])
        Attendee temp = attendees[i + 1];
        attendees[i + 1] = attendees[high];
        attendees[high] = temp;

        return i + 1;
    }
}