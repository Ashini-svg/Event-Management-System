package com.eventmgmt.structures.hashing;

import com.eventmgmt.model.Attendee;

public class AttendeeHashManager {
    private HashTable attendeeTable;

    public AttendeeHashManager() {
        this.attendeeTable = new HashTable(20);
    }

    public void registerAttendee(Attendee attendee) {
        if (attendee != null && attendee.getAttendeeId() != null) {
            attendeeTable.put(attendee.getAttendeeId(), attendee);
        }
    }

    public Attendee searchAttendee(String key) {
        return attendeeTable.get(key);
    }

    public Attendee removeAttendee(String attendeeId) {
        return attendeeTable.remove(attendeeId);
    }

    public int getTotalAttendees() {
        return attendeeTable.getSize();
    }
}