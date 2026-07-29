package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class ListNode {

    private Attendee attendee;
    private ListNode next;

    public ListNode(Attendee attendee) {
        this.attendee = attendee;
        this.next = null;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}