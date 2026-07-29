package com.eventmgmt.structures.trees;

import com.eventmgmt.model.Event;

public class BSTNode {
	
	private Event event;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(Event event) {
        this.event = event;
        left = null;
        right = null;
    }
    
    //Getters
    public Event getEvent() {
    	return event;
    }
    
    public BSTNode getLeft() {
        return left;
    }

    public BSTNode getRight() {
        return right;
    }

    // Setters
    public void setEvent(Event event) {
        this.event = event;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
}
