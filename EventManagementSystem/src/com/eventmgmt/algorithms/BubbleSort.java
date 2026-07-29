package com.eventmgmt.algorithms;

import com.eventmgmt.model.Attendee;

public class BubbleSort {

    // Sort Attendees by Name
    public void sortByName(Attendee[] attendees) {

        if (attendees == null) {
            return;
        }

        int n = attendees.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (attendees[j] != null &&
                    attendees[j + 1] != null &&
                    attendees[j].getName().compareToIgnoreCase(attendees[j + 1].getName()) > 0) {

                    // Swap
                    Attendee temp = attendees[j];
                    attendees[j] = attendees[j + 1];
                    attendees[j + 1] = temp;
                }
            }
        }
    }

    // Display Sorted Attendees
    public void displayAttendees(Attendee[] attendees) {

        if (attendees == null) {
            return;
        }

        for (Attendee attendee : attendees) {

            if (attendee != null) {
                System.out.println(attendee);
                System.out.println("---------------------------");
            }
        }
    }
}