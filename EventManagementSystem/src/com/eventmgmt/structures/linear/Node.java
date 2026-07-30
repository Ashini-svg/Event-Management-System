package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class Node {

    private Attendee attendee;
    private Node next;

    // Constructor
    public Node(Attendee attendee) {
        this.attendee = attendee;
        this.next = null;
    }

    // Getter
    public Attendee getAttendee() {
        return attendee;
    }

    // Setter
    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    // Getter
    public Node getNext() {
        return next;
    }

    // Setter
    public void setNext(Node next) {
        this.next = next;
    }
}