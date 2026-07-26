package com.eventmgmt.structures.trees;

import com.eventmgmt.model.Event;

public class BST {
	
	//Root node of the Binary Search Tree
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

        //Go to left subtree
        if (event.compareTo(current.event) < 0) {
            current.left = insertRecursive(current.left, event);
        }

        //Go to right subtree
        else if (event.compareTo(current.event) > 0) {
            current.right = insertRecursive(current.right, event);
        }

        //Duplicate IDs are ignored
        return current;
    }

    //SEARCH
    public Event search(String eventId) {

        BSTNode current = root;

        while (current != null) {

            int comparison = eventId.compareTo(current.event.getEventId());

            if (comparison == 0) {
                return current.event;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
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

        int comparison = eventId.compareTo(current.event.getEventId());

        if (comparison < 0) {

            current.left = deleteRecursive(current.left, eventId);

        } else if (comparison > 0) {

            current.right = deleteRecursive(current.right, eventId);

        } else {

            //Case 1 : No children
            if (current.left == null && current.right == null) {
                return null;
            }

            //Case 2 : One child
            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            //Case 3 : Two children
            Event smallest = findSmallest(current.right);

            current.event = smallest;

            current.right = deleteRecursive(
                    current.right,
                    smallest.getEventId());
        }

        return current;
    }

    //Find smallest node in a subtree
    private Event findSmallest(BSTNode current) {

        while (current.left != null) {
            current = current.left;
        }

        return current.event;
    }

    //INORDER
    public void inorder() {
        inorderRecursive(root);
    }

    private void inorderRecursive(BSTNode current) {

        if (current != null) {

            inorderRecursive(current.left);

            System.out.println(current.event);
            System.out.println();

            inorderRecursive(current.right);
        }
    }

    //PREORDER
    public void preorder() {
        preorderRecursive(root);
    }

    private void preorderRecursive(BSTNode current) {

        if (current != null) {

            System.out.println(current.event);
            System.out.println();

            preorderRecursive(current.left);

            preorderRecursive(current.right);
        }
    }

    //POSTORDER
    public void postorder() {
        postorderRecursive(root);
    }

    private void postorderRecursive(BSTNode current) {

        if (current != null) {

            postorderRecursive(current.left);

            postorderRecursive(current.right);

            System.out.println(current.event);
            System.out.println();
        }
    }	
}
