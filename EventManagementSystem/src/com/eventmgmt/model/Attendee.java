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
		if (this.attendeeId == null || other == null || other.attendeeId == null) {
			return 0;
		}
		return this.attendeeId.compareToIgnoreCase(other.attendeeId);
	}
	
	// Used by the Set ADT to catch duplicate registrations
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Attendee attendee = (Attendee) obj;
		if (this.email != null && attendee.email != null) {
			return this.email.equalsIgnoreCase(attendee.email);
		}
		return Objects.equals(this.attendeeId, attendee.attendeeId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email != null ? email.toLowerCase() : attendeeId);
	}
	
	// Overriding toString() for clear outputs
	@Override
	public String toString() {
		return  "Attendee ID: " + attendeeId + 
				"\nName: " + attendeeName +
				"\nEmail: " + email;
	}
}
