package com.eventmgmt.model;

public class Venue {
	
    // Attributes
    private String venueId;
    private String venueName;
    private String location;
    private int capacity;
    private boolean available;
    

    // Default Constructor
    public Venue() {
    }


    // Parameterized Constructor
    public Venue(String venueId, String venueName, 
                 String location, int capacity) {

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


    // Book venue
    public void bookVenue() {
        available = false;
    }


    // Release venue
    public void releaseVenue() {
        available = true;
    }


    // Display venue details
    @Override
    public String toString() {

        return "Venue ID: " + venueId +
                "\nVenue Name: " + venueName +
                "\nLocation: " + location +
                "\nCapacity: " + capacity +
                "\nAvailable: " + available;
    }
}