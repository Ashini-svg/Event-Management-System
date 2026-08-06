package com.eventmgmt.structures.hashing;

import com.eventmgmt.model.Attendee;

public class SetADT {
    private HashTable table;

    public SetADT() {
        this.table = new HashTable(20);
    }

    // Adds unique elements only
    public boolean add(Attendee attendee) {
        if (attendee == null || attendee.getAttendeeId() == null) {
            return false;
        }

        // Duplicate prevention check
        if (contains(attendee.getAttendeeId())) {
            System.out.println("Duplicate registration prevented for ID: " + attendee.getAttendeeId());
            return false;
        }

        table.put(attendee.getAttendeeId(), attendee);
        return true;
    }

    public boolean contains(String key) {
        return table.get(key) != null;
    }

    public boolean remove(String key) {
        return table.remove(key) != null;
    }

    public int size() {
        return table.getSize();
    }
}