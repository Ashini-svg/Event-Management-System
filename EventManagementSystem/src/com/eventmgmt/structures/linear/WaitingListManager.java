package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class WaitingListManager {

    private Queue waitingQueue;

    // Constructor
    public WaitingListManager() {
        waitingQueue = new Queue();
    }

    // Add Attendee
    public void addToWaitingList(Attendee attendee) {

        waitingQueue.enqueue(attendee);

        System.out.println(attendee.getName() + " added to waiting list.");
    }

    // Process Next Attendee
    public Attendee processNextAttendee() {

        if (waitingQueue.isEmpty()) {
            System.out.println("Waiting list is empty.");
            return null;
        }

        Attendee attendee = waitingQueue.dequeue();

        System.out.println(attendee.getName() + " has been registered.");

        return attendee;
    }

    // View Next Attendee
    public void viewNextAttendee() {

        if (waitingQueue.isEmpty()) {
            System.out.println("Waiting list is empty.");
            return;
        }

        System.out.println("Next Attendee: " + waitingQueue.peek().getName());
    }

    // Get Waiting Count
    public int getWaitingCount() {
        return waitingQueue.getSize();
    }

    // Display Waiting List
    public void displayWaitingList() {

        waitingQueue.display();
    }
}