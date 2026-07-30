package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class LinkedList {

    private ListNode head;

    // Constructor
    public LinkedList() {
        head = null;
    }

    // Add Attendee
    public void addAttendee(Attendee attendee) {

        ListNode newNode = new ListNode(attendee);

        if (head == null) {
            head = newNode;
            return;
        }

        ListNode current = head;

        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(newNode);
    }

    // Display All Attendees
    public void displayAttendees() {

        if (head == null) {
            System.out.println("No attendees found.");
            return;
        }

        ListNode current = head;

        while (current != null) {

            System.out.println(current.getAttendee());
            System.out.println("---------------------------");

            current = current.getNext();
        }
    }

    // Search Attendee
    public Attendee searchAttendee(String attendeeId) {

        ListNode current = head;

        while (current != null) {

            if (current.getAttendee().getAttendeeId().equalsIgnoreCase(attendeeId)) {
                return current.getAttendee();
            }

            current = current.getNext();
        }

        return null;
    }

    // Update Attendee
    public boolean updateAttendee(String attendeeId, String newName, String newEmail) {

        ListNode current = head;

        while (current != null) {

            if (current.getAttendee().getAttendeeId().equalsIgnoreCase(attendeeId)) {

                current.getAttendee().setName(newName);
                current.getAttendee().setEmail(newEmail);

                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    // Delete Attendee
    public boolean deleteAttendee(String attendeeId) {

        if (head == null) {
            return false;
        }

        if (head.getAttendee().getAttendeeId().equalsIgnoreCase(attendeeId)) {
            head = head.getNext();
            return true;
        }

        ListNode current = head;

        while (current.getNext() != null) {

            if (current.getNext().getAttendee().getAttendeeId().equalsIgnoreCase(attendeeId)) {

                current.setNext(current.getNext().getNext());
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    // Get Head Node
    public ListNode getHead() {
        return head;
    }
}
