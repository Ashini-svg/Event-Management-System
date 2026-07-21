package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class Node {

    private Attendee attendee;
    private Node next;

    public Node(Attendee attendee) {
        this.attendee = attendee;
        this.next = null;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}