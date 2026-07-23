package com.eventmgmt.structures.trees;

import com.eventmgmt.model.Attendee;

public class AVLTree {
	private AVLNode root;
	
	public AVLTree() {
		this.root = null;
	}
	
	// Helper Utility Methods
	// Gets node height cleanly (returns 0 for null nodes)
	private int getHeight(AVLNode node) {
		return (node == null) ? 0 : node.getHeight();
	}
	
	// Returns node balance factor
	private int getBalance(AVLNode node) {
		return (node == null) ? 0 : getHeight(node.getLeft()) - getHeight(node.getRight());
	}
	
	// Updates height of a node based on its children
	private void updateHeight(AVLNode node) {
		if (node != null) {
			node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
		}
	}
	
	// AVL Rotations
	// Right Rotati0n (LL Case)
	private AVLNode rotateRight(AVLNode y) {
		AVLNode x = y.getLeft();
		AVLNode T2 = x.getRight();
		
		// Perform rotation
		y.setRight(x);
		x.setLeft(T2);
		
		// Update heights
		updateHeight(y);
		updateHeight(x);
		
		return x; // New root
	}
	
	// Left Rotation (RR Case)
	private AVLNode rotateLeft(AVLNode x) {
		AVLNode y = x.getRight();
		AVLNode T2 = y.getLeft();
		
		// Perform rotation
		y.setLeft(x);
		x.setRight(T2);
		
		// Update heights
		updateHeight(x);
		updateHeight(y);
		
		return y; // New root
	}
	
	// Core Data Structure Functions
	// Insert Method
	public void insert(Attendee attendee) {
		if (attendee == null) return;
		this.root = insertRecursive(this.root, attendee);
	}
	
	private AVLNode insertRecursive(AVLNode node, Attendee attendee) {
		// Standard BST Insertion
		if (node == null) {
			return new AVLNode(attendee);
		}
		int comparison = attendee.getAttendeeId().compareTo(node.getAttendee().getAttendeeId());
		
		if (comparison < 0) {
			node.setLeft(insertRecursive(node.getLeft(), attendee));
			
		} else if (comparison < 0) {
			node.setLeft(insertRecursive(node.getRight(), attendee));
			
		} else {
			// Duplicate attendee IDs not allowed
			return node;
		}
		updateHeight(node);
		
		// Get balance factor to check for imbalance
		int balance = getBalance(node);
		
		// Left Left (LL) Case
        if (balance > 1 && attendee.getAttendeeId().compareTo(node.getLeft().getAttendee().getAttendeeId()) < 0) {
            return rotateRight(node);
        }

        // Right Right (RR) Case
        if (balance < -1 && attendee.getAttendeeId().compareTo(node.getRight().getAttendee().getAttendeeId()) > 0) {
            return rotateLeft(node);
        }

        // Left Right (LR) Case
        if (balance > 1 && attendee.getAttendeeId().compareTo(node.getLeft().getAttendee().getAttendeeId()) > 0) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        // Right Left (RL) Case
        if (balance < -1 && attendee.getAttendeeId().compareTo(node.getRight().getAttendee().getAttendeeId()) < 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }
        return node;
	}
	
	// Delete Method
	public void delete(String attendeeId) {
        this.root = deleteRecursive(this.root, attendeeId);
    }

    private AVLNode deleteRecursive(AVLNode root, String attendeeId) {
        if (root == null) return null;

        int comparison = attendeeId.compareTo(root.getAttendee().getAttendeeId());

        if (comparison < 0) {
            root.setLeft(deleteRecursive(root.getLeft(), attendeeId));
        } else if (comparison > 0) {
            root.setRight(deleteRecursive(root.getRight(), attendeeId));
        } else {
            // Node to be deleted found!
            // Node with only one child or no child
            if ((root.getLeft() == null) || (root.getRight() == null)) {
                AVLNode temp = (root.getLeft() != null) ? root.getLeft() : root.getRight();

                if (temp == null) {
                    // No child case
                    temp = root;
                    root = null;
                } else {
                    // One child case
                    root = temp;
                }
            } else {
                // Node with two children: Get the in-order successor (smallest in right subtree)
                AVLNode temp = getMinValueNode(root.getRight());
                root.setAttendee(temp.getAttendee());
                root.setRight(deleteRecursive(root.getRight(), temp.getAttendee().getAttendeeId()));
            }
        }

        if (root == null) return null;

        // Update height
        updateHeight(root);

        // Check balance factor
        int balance = getBalance(root);

        // Balance rotations if required after deletion
        if (balance > 1 && getBalance(root.getLeft()) >= 0) {
            return rotateRight(root);
        }

        if (balance > 1 && getBalance(root.getLeft()) < 0) {
            root.setLeft(rotateLeft(root.getLeft()));
            return rotateRight(root);
        }

        if (balance < -1 && getBalance(root.getRight()) <= 0) {
            return rotateLeft(root);
        }

        if (balance < -1 && getBalance(root.getRight()) > 0) {
            root.setRight(rotateRight(root.getRight()));
            return rotateLeft(root);
        }

        return root;
    }
    
    private AVLNode getMinValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }
	
    // Search Method
    public Attendee search(String attendeeId) {
        AVLNode node = searchRecursive(this.root, attendeeId);
        return (node != null) ? node.getAttendee() : null;
    }

    private AVLNode searchRecursive(AVLNode root, String attendeeId) {
        if (root == null || root.getAttendee().getAttendeeId().equals(attendeeId)) {
            return root;
        }

        if (attendeeId.compareTo(root.getAttendee().getAttendeeId()) < 0) {
            return searchRecursive(root.getLeft(), attendeeId);
        }

        return searchRecursive(root.getRight(), attendeeId);
    }
    
    // Traversal Methods
    // In-order Traversal
    public void inorderTraversal() {
        System.out.println("\n--- Inorder Traversal (Attendee List) ---");
        inorderRecursive(this.root);
    }

    private void inorderRecursive(AVLNode node) {
        if (node != null) {
            inorderRecursive(node.getLeft());
            System.out.println(node.getAttendee());
            System.out.println("---------------------------------");
            inorderRecursive(node.getRight());
        }
    }

    // Pre-order Traversal
    public void preorderTraversal() {
        System.out.println("\n--- Preorder Traversal ---");
        preorderRecursive(this.root);
    }

    private void preorderRecursive(AVLNode node) {
        if (node != null) {
            System.out.println(node.getAttendee());
            preorderRecursive(node.getLeft());
            preorderRecursive(node.getRight());
        }
    }

    // Post-order Traversal
    public void postorderTraversal() {
        System.out.println("\n--- Postorder Traversal ---");
        postorderRecursive(this.root);
    }

    private void postorderRecursive(AVLNode node) {
        if (node != null) {
            postorderRecursive(node.getLeft());
            postorderRecursive(node.getRight());
            System.out.println(node.getAttendee());
        }
    }
}
