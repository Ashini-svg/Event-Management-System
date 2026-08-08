package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class WaitingListManager {
	
	// Pairs a waiting attendee with the specific event they are waiting for
	public static class WaitingEntry {
		private final String eventId;
		private final Attendee attendee;
		
		public WaitingEntry(String eventId, Attendee attendee) {
			this.eventId = eventId;
			this.attendee = attendee;
		}
		
		public String getEventId() {
			return eventId;
		}
		
		public Attendee getAttendee() {
			return attendee;
		}
		
		@Override
		public String toString() {
			return "Event " + eventId + " - " + attendee.getName() + " (" + attendee.getAttendeeId() + ")";
		}
	}

    private Queue<WaitingEntry> waitingQueue;

    // Constructor
    public WaitingListManager() {
        waitingQueue = new Queue<>();
    }

    // Add Attendee to the waiting list for a specific event
    public void addToWaitingList(String eventId, Attendee attendee) {

        waitingQueue.enqueue(new WaitingEntry(eventId, attendee));

        System.out.println(attendee.getName() + " added to waiting list for event " + eventId + ".");
    }

    // Process the next attendee who is actually waiting for the given event
    // Anyone else still in the queue keeps their original relative order
    public Attendee processNextAttendeeForEvent(String eventId) {

        if (waitingQueue.isEmpty()) {
            System.out.println("Waiting list is empty.");
            return null;
        }

        int originalSize = waitingQueue.getSize();
        WaitingEntry matched = null;
        Queue<WaitingEntry> remaining = new Queue<>();
        
        for (int i = 0; i < originalSize; i++) {
        	WaitingEntry entry = waitingQueue.dequeue();
        	
        	if (matched == null && entry.getEventId().equalsIgnoreCase(eventId)) {
        		matched = entry;
        		
        	} else {
        		remaining.enqueue(entry);
        	}
        }
        
        // Restore everyone who was not matched, in their original order
        while (!remaining.isEmpty()) {
        	waitingQueue.enqueue(remaining.dequeue());
        }
        
        if (matched == null) {
        	System.out.println("No one is waiting for event " + eventId +".");
        	return null;
        }
        
        System.out.println(matched.getAttendee().getName() + " has been registered for event " + eventId + ".");
        return matched.getAttendee();
    }
    
    // Get Waiting Count (total across all events)
    public int getWaitingCount() {
    	return waitingQueue.getSize();
    }

    // Display Waiting List (shows which event each attendee is waiting for)
    public void displayWaitingList() {

        waitingQueue.display();
    }
}