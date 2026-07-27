package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class WaitingListManager {

    private Queue waitingQueue;

    public WaitingListManager() {
        this.waitingQueue = new Queue();
    }

    public void addToWaitingList(Attendee attendee) {
        waitingQueue.enqueue(attendee);
        System.out.println(attendee.getName() + " added to the waiting list.");
    }

    public Attendee processNextAttendee() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Waiting list is currently empty.");
            return null;
        }
        Attendee processed = waitingQueue.dequeue();
        System.out.println("Processed registration for: " + processed.getName());
        return processed;
    }

    public void viewNextInLine() {
        if (waitingQueue.isEmpty()) {
            System.out.println("No one in the waiting list.");
        } else {
            System.out.println("Next in line: " + waitingQueue.peek().getName());
        }
    }

    public int getWaitingCount() {
        return waitingQueue.getSize();
    }
}