package com.eventmgmt.structures.trees;

import com.eventmgmt.model.Event;

public class BST {
	
	//Root node of the BST
    private BSTNode root;

    //Constructor
    public BST() {
        root = null;
    }

    //INSERT
    public void insert(Event event) {
        root = insertRecursive(root, event);
    }

    private BSTNode insertRecursive(BSTNode current, Event event) {

        //Empty position found
        if (current == null) {
            return new BSTNode(event);
        }
        
        int comparison = event.getEventId().compareToIgnoreCase(current.getEvent().getEventId());
        
        if (comparison < 0) {
            current.setLeft(insertRecursive(current.getLeft(), event));  //Go to left subtree
            
        } else if (comparison > 0) {
            current.setRight(insertRecursive(current.getRight(), event));  //Go to right subtree
        }

        //Duplicate IDs are ignored
        return current;
    }

    //SEARCH
    public Event search(String eventId) {

        BSTNode current = root;

        while (current != null) {

        	int comparison = eventId.compareToIgnoreCase(current.getEvent().getEventId());

            if (comparison == 0) {
            	return current.getEvent();
            	}

            if (comparison < 0) {
                current = current.getLeft();
            } else {
            	current = current.getRight();
            }
         }

        return null;
    }

    //DELETE
    public void delete(String eventId) {
        root = deleteRecursive(root, eventId);
    }

    private BSTNode deleteRecursive(BSTNode current, String eventId) {

        if (current == null) {
            return null;
        }

        int comparison = eventId.compareToIgnoreCase(current.getEvent().getEventId());

        if (comparison < 0) {
        	current.setLeft(deleteRecursive(current.getLeft(), eventId));

        } else if (comparison > 0) {
        	current.setRight(deleteRecursive(current.getRight(), eventId));

        } else {

            //Case 1 : No children
        	if (current.getLeft() == null && current.getRight() == null) {
        		return null;
            }

            //Case 2 : One child
        	if (current.getLeft() == null)
        	    return current.getRight();

        	if (current.getRight() == null)
        	    return current.getLeft();

            //Case 3 : Two children
            Event smallest = findSmallest(current.getRight());
            
            current.setEvent(smallest);
            
            current.setRight(deleteRecursive(
                    current.getRight(),
                    smallest.getEventId()));
        }

        return current;
    }

    //Find smallest node in a subtree
    private Event findSmallest(BSTNode current) {

    	while (current.getLeft() != null) {
    	    current = current.getLeft();
    	}

    	return current.getEvent();
    }

    //INORDER
    public void inorder() {
        inorderRecursive(root);
    }

    private void inorderRecursive(BSTNode current) {

        if (current != null) {

        	inorderRecursive(current.getLeft());

        	System.out.println(current.getEvent());
            System.out.println();

            inorderRecursive(current.getRight());
        }
    }

    //PREORDER
    public void preorder() {
        preorderRecursive(root);
    }

    private void preorderRecursive(BSTNode current) {

        if (current != null) {

        	System.out.println(current.getEvent());
            System.out.println();

            preorderRecursive(current.getLeft());

            preorderRecursive(current.getRight());
        }
    }

    //POSTORDER
    public void postorder() {
        postorderRecursive(root);
    }

    private void postorderRecursive(BSTNode current) {

        if (current != null) {

        	postorderRecursive(current.getLeft());

        	postorderRecursive(current.getRight());

        	System.out.println(current.getEvent());
            System.out.println();
        }
    }
}
