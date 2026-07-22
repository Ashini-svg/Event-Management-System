package com.eventmgmt.model;

//Implements Comparable so Venue objects can be sorted.
public class Venue implements Comparable<Venue> {
	
    // Attributes
    private String venueId;
    private String venueName;
    private String location;
    private int capacity;
    private boolean available;

    // Default Constructor
    public Venue() {}

    // Parameterized Constructor
    public Venue(String venueId, String venueName, String location, int capacity) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.location = location;
        this.capacity = capacity;
        this.available = true;
    }

    // Getters
    public String getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    // Overriding toString() for clear outputs
    @Override
    public String toString() {
        return "Venue ID: " + venueId +
                "\nVenue Name: " + venueName +
                "\nLocation: " + location +
                "\nCapacity: " + capacity +
                "\nAvailable: " + available;    
      
    }
 
    
    // Allows Venue objects to be compared alphabetically by venue name.
    // This is used by sorting algorithms such as Merge Sort.
    @Override
    public int compareTo(Venue other) {
        return this.venueName.compareTo(other.venueName);
    }
}