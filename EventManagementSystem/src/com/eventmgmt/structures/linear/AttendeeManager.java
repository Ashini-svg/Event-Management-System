package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class AttendeeManager {

    private LinkedList attendeeList;

    // Constructor
    public AttendeeManager() {
        attendeeList = new LinkedList();
    }

    // Add an Attendee
    public void addAttendee(Attendee attendee) {

        attendeeList.addAttendee(attendee);

        System.out.println("Attendee added successfully.");
    }

    // Display Attendees
    public void displayAttendees() {

        attendeeList.displayAttendees();
    }

    // Search Attendee
    public Attendee searchAttendee(String attendeeId) {

        return attendeeList.searchAttendee(attendeeId);
    }

    // Update Attendee
    public boolean updateAttendee(String attendeeId, String newName, String newEmail) {

        return attendeeList.updateAttendee(attendeeId, newName, newEmail);
    }

    // Delete Attendee
    public boolean deleteAttendee(String attendeeId) {

        return attendeeList.deleteAttendee(attendeeId);
    }

    // Return Linked List
    public LinkedList getAttendeeList() {

        return attendeeList;
    }
}