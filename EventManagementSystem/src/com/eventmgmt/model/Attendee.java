package com.eventmgmt.model;

import java.util.Objects;

public class Attendee implements Comparable<Attendee> {
	
	// Attributes
	private String attendeeId;
	private String attendeeName;
	private String email;
	
	// Default Constructor
	public Attendee() {}
	
	// Parameterized Constructor
	public Attendee(String attendeeId, String attendeeName, String email) {
		this.attendeeId = attendeeId;
		this.attendeeName = attendeeName;
		this.email = email;	
	}
	
	// Getters
	public String getAttendeeId() {
		return attendeeId;
	}
	
	public String getName() {
		return attendeeName;
	}
	
	public String getEmail() {
		return email;
	}
	
	// Setters
	public void setAttendeeId(String attendeeId) {
		this.attendeeId = attendeeId;
	}
	
	public void setName(String attendeeName) {
		this.attendeeName = attendeeName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Allows sorting/inserting in the AVL Tree
	@Override
	public int compareTo(Attendee other) {
		return this.attendeeId.compareTo(other.attendeeId);
	}
	
	// Used by the Set ADT to catch duplicate registrations
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Attendee attendee = (Attendee) o;
		return Objects.equals(email, attendee.email);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	
	// Overriding toString() for clear outputs
	@Override
	public String toString() {
		return  "Attendee ID: " + attendeeId + 
				"\nName: " + attendeeName +
				"\nEmail: " + email;
	}
}
