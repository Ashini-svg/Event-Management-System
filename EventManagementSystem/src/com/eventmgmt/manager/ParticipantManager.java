package com.eventmgmt.manager;

import com.eventmgmt.model.Attendee;
import com.eventmgmt.structures.linear.LinkedList;

public class ParticipantManager {

    private LinkedList attendeeList;

    // Constructor
    public ParticipantManager() {
        attendeeList = new LinkedList();
    }

    // Add Participant
    public void addParticipant(Attendee attendee) {

        attendeeList.addAttendee(attendee);

        System.out.println("Participant added successfully.");
    }

    // Display Participants
    public void displayParticipants() {

        attendeeList.displayAttendees();
    }

    // Search Participant
    public Attendee searchParticipant(String attendeeId) {

        return attendeeList.searchAttendee(attendeeId);
    }

    // Update Participant
    public boolean updateParticipant(String attendeeId, String newName, String newEmail) {

        return attendeeList.updateAttendee(attendeeId, newName, newEmail);
    }

    // Delete Participant
    public boolean deleteParticipant(String attendeeId) {

        return attendeeList.deleteAttendee(attendeeId);
    }

    // Return Linked List
    public LinkedList getParticipantList() {

        return attendeeList;
    }
}