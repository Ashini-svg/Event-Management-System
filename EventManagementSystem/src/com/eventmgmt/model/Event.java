package com.eventmgmt.model;

public class Event {

    // Attributes
    private String eventId;
    private String eventName;
    private String eventType;
    private String venue;
    private String date;
    private String time;
    private int capacity;
    private int registeredParticipants;

    // Default Constructor
    public Event() {
    }

    // Parameterized Constructor
    public Event(String eventId, String eventName, String eventType,
                 String venue, String date, String time,
                 int capacity) {

        this.eventId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.registeredParticipants = 0;
    }

    // Getters
    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRegisteredParticipants() {
        return registeredParticipants;
    }

    // Setters
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRegisteredParticipants(int registeredParticipants) {
        this.registeredParticipants = registeredParticipants;
    }

    // Register a participant
    public boolean registerParticipant() {
        if (registeredParticipants < capacity) {
            registeredParticipants++;
            return true;
        }
        return false;
    }

    // Cancel a registration
    public boolean cancelRegistration() {
        if (registeredParticipants > 0) {
            registeredParticipants--;
            return true;
        }
        return false;
    }

    // Check whether event is full
    public boolean isFull() {
        return registeredParticipants >= capacity;
    }

    // Display event details
    @Override
    public String toString() {
        return "Event ID: " + eventId +
                "\nEvent Name: " + eventName +
                "\nType: " + eventType +
                "\nVenue: " + venue +
                "\nDate: " + date +
                "\nTime: " + time +
                "\nCapacity: " + capacity +
                "\nRegistered: " + registeredParticipants;
    }
}
