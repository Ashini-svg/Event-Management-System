package com.eventmgmt.structures.hashing;

import com.eventmgmt.model.Attendee;

public class HashNode {
    String key; // e.g., attendeeId or email
    Attendee value;
    HashNode next; // Pointer for separate chaining

    public HashNode(String key, Attendee value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public String getKey() {
        return key;
    }

    public Attendee getValue() {
        return value;
    }
}