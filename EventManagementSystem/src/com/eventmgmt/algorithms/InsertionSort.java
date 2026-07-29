package com.eventmgmt.algorithms;

import com.eventmgmt.model.Booking;
import com.eventmgmt.model.Event;

public class InsertionSort {
	// Sorts an array of booking objects chronologically by booking time-stamp
	public static void sortByDate(Booking[] bookings) {
		if (bookings == null || bookings.length <= 1) {
			return;
		}
		int n = bookings.length;
		
		for (int i = 1; i < n; i++) {
			Booking key = bookings[i];
			
			// Skip null entries if array is not fully packed
			if (key == null) continue;
			
			int j = i -1;
			
			// Shift elements of bookings that are greater than key.getBookingDateTime()
			while (j >= 0 && bookings[j] != null && bookings[j].getBookingDate().isAfter(key.getBookingDate())) {
				bookings[j + 1] = bookings[j];
				j = j - 1;
			}
			bookings[j + 1] = key;
		}
	}
	
	// Helper method to sort an array of Event objects chronologically by event date and time
	public static void sortEventsByDate(Event[] events) {
		if (events == null || events.length <= 1) {
			return;
		}
		int n = events.length;
		
		for (int i = 1; i < n; i++) {
			Event key = events[i];
			
			if (key == null) continue;
			
			int j = i - 1;
			
			while (j >= 0 && events[j] != null && events[j].getEventDateTime().isAfter(key.getEventDateTime())) {
				events[j + 1] = events[j];
				j = j - 1;
			}
			events[j + 1] = key;
		}
	}
}
