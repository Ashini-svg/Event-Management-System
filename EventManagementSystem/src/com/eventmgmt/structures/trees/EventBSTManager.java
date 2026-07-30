package com.eventmgmt.structures.trees;

import com.eventmgmt.model.Event;

public class EventBSTManager {

    //BST instance for managing events
    private final BST bst;

    //Constructor
    public EventBSTManager() {
        this.bst = new BST();
    }

    //Add an event to the BST
    public void addEvent(Event event) {
        if (event != null && event.getEventId() != null) {
            bst.insert(event);
        }
    }

    //Search for an event using its Event ID
    public Event searchEvent(String eventId) {
        if (eventId == null || eventId.isBlank()) {
            return null;
        }
        return bst.search(eventId);
    }

    //Delete an event using its Event ID
    public void deleteEvent(String eventId) {
        if (eventId != null && !eventId.isBlank()) {
            bst.delete(eventId);
        }
    }

    //Display all events in ascending order (inorder traversal)
    public void displayInOrder() {
        bst.inorder();
    }

    //Display events using preorder traversal
    public void displayPreOrder() {
        bst.preorder();
    }

    //Display events using postorder traversal
    public void displayPostOrder() {
        bst.postorder();
    }
}
