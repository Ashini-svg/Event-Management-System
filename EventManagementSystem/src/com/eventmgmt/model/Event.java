package com.eventmgmt.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event> {
    // Attributes
    private String eventId;
    private String title;
    private String category;
    private String venueId;
    private LocalDateTime eventDateTime;
    private int capacity;
    private int attendeesCount;

    // Default Constructor
    public Event() {}

    // Parameterized Constructor
    public Event(String eventId, String title, String category, String venueId, LocalDateTime eventDateTime, int capacity) {

        this.eventId = eventId;
        this.title = title;
        this.category = category;
        this.venueId = venueId;
        this.eventDateTime = eventDateTime;
        this.capacity = capacity;
        this.attendeesCount = 0;
    }

    // Getters
    public String getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getVenueId() {
        return venueId;
    }

    public LocalDateTime getEventDateTime() {
    	return eventDateTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAttendeesCount() {
        return attendeesCount;
    }

    // Setters
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
    	this.eventDateTime = eventDateTime;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAttendeesCount(int attendeesCount) {
        this.attendeesCount = attendeesCount;
    }
    
    // Helper methods
    public boolean isFull() {
    	return this.attendeesCount >= this.capacity;
    }
    
    public void incrementAttendees() {
    	if (!isFull()) {
    		this.attendeesCount++;
    	}
    }
    
    public void decrementAttendees() {
    	if (this.attendeesCount > 0) {
    		this.attendeesCount--;
    	}
    }
    
    //Binary Search Tree comparison logic based on ID keys
    @Override
    public int compareTo(Event other) {
    	if (this.eventId == null || other == null || other.eventId == null) {
    		return 0;
    	}
    	return this.eventId.compareToIgnoreCase(other.eventId);
    }

    // Overriding toString() for clear outputs
    @Override
    public String toString() {
        return "Event ID: " + eventId +
                "\nTitle: " + title +
                "\nCategory: " + category +
                "\nVenueID: " + venueId +
                "\nDate & Time: " + eventDateTime +
                "\nCapacity: " + capacity +
                "\nRegistered Attendees: " + attendeesCount;
    }
}
