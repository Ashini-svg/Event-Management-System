package com.eventmgmt.structures.trees;

import com.eventmgmt.model.Event;

public class BSTNode {
	
	Event event;
    BSTNode left;
    BSTNode right;

    public BSTNode(Event event) {
        this.event = event;
        left = null;
        right = null;
    }
}
