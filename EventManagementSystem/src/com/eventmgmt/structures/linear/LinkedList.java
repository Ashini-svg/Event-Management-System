package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class LinkedList {

    private Node head;

    // Constructor
    public LinkedList() {
        head = null;
    }

    // Add Attendee
    public void addAttendee(Attendee attendee) {

        Node newNode = new Node(attendee);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;

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

        Node current = head;

        while (current != null) {

            System.out.println(current.getAttendee());
            System.out.println("---------------------------");

            current = current.getNext();
        }
    }

    // Search Attendee
    public Attendee searchAttendee(String attendeeId) {

        Node current = head;

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

        Node current = head;

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

        Node current = head;

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
    public Node getHead() {
        return head;
    }
}
