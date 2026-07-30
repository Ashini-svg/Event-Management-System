package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class ListNode {

    private Attendee attendee;
    private ListNode next;

    // Constructor
    public ListNode(Attendee attendee) {
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
    public ListNode getNext() {
        return next;
    }

    // Setter
    public void setNext(ListNode next) {
        this.next = next;
    }
}