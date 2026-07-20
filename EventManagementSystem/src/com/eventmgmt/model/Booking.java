package com.eventmgmt.model;

import java.time.LocalDateTime;

public class Booking {
	// Attributes
	private String bookingId;
	private String eventId;
	private String attendeeId;
	private LocalDateTime bookingDateTime;
	
	// Default Constructor
	public Booking() {}
	
	// Parameterized Constructor
	public Booking(String bookingId, String eventId, String attendeeId, LocalDateTime bookingDateTime) {
		this.bookingId = bookingId;
		this.eventId = eventId;
		this.attendeeId = attendeeId;
		this.bookingDateTime = bookingDateTime;
 	}
	
	// Getters
	public String getBookingId() {
		return bookingId;
	}
	
	public String getEventId() {
		return eventId;
	}
	
	public String getAttendeeId() {
		return attendeeId;
	}
	
	public LocalDateTime getBookingTimestamp() {
		return bookingDateTime;
	}
	
	// Setters
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public void setAttendeeId(String attendeeId) {
		this.attendeeId = attendeeId;
	}
	
	public void setBookingTimestamp(LocalDateTime bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}
	
	// Overriding toString() for clear outputs
	@Override
	public String toString() {
		return "Booking ID: " + bookingId +
				"\nEvent ID: " + eventId +
				"\nAttendee ID: " + attendeeId +
				"\nDate & Time: " + bookingDateTime;
	}
}
