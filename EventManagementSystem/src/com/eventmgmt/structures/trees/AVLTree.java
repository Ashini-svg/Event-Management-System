package com.eventmgmt.structures.trees;

public class AVLTree<T extends Comparable<T>> {
	private AVLNode<T> root;
	private int size;
	
	public AVLTree() {
		this.root = null;
		this.size = 0;
	}
	
	// Helper Utility Methods
	// Gets node height cleanly (returns 0 for null nodes)
	private int getHeight(AVLNode<T> node) {
		return (node == null) ? 0 : node.getHeight();
	}
	
	// Returns node balance factor
	private int getBalance(AVLNode<T> node) {
		return (node == null) ? 0 : getHeight(node.getLeft()) - getHeight(node.getRight());
	}
	
	// Updates height of a node based on its children
	private void updateHeight(AVLNode<T> node) {
		if (node != null) {
			node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
		}
	}
	
	// Returns the total number of elements in the tree
	public int getSize() {
		return this.size;
	}
	
	// Returns the height of the root (overall tree height)
	public int getTreeHeight() {
		return getHeight(this.root);
	}
	
	// Checks if the tree is empty
	public boolean isEmpty() {
		return this.root == null;
	}
	
	// AVL Rotations
	// Right Rotati0n (LL Case)
	private AVLNode<T> rotateRight(AVLNode<T> y) {
		AVLNode<T> x = y.getLeft();
		AVLNode<T> T2 = x.getRight();
		
		// Perform rotation
		x.setRight(y);
		y.setLeft(T2);
		
		// Update heights
		updateHeight(y);
		updateHeight(x);
		
		return x; // New root
	}
	
	// Left Rotation (RR Case)
	private AVLNode<T> rotateLeft(AVLNode<T> x) {
		AVLNode<T> y = x.getRight();
		AVLNode<T> T2 = y.getLeft();
		
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
	public void insert(T data) {
		if (data == null) return;
		this.root = insertRecursive(this.root, data);
	}
	
	private AVLNode<T> insertRecursive(AVLNode<T> node, T data) {
		// Standard BST Insertion
		if (node == null) {
			size++;
			return new AVLNode<>(data);
		}
		int comparison = data.compareTo(node.getData());
		
		if (comparison < 0) {
			node.setLeft(insertRecursive(node.getLeft(), data));
			
		} else if (comparison > 0) {
			node.setRight(insertRecursive(node.getRight(), data));
			
		} else {
			return node;  // Duplicate attendee IDs not allowed
		}
		updateHeight(node);
		int balance = getBalance(node);  // Get balance factor to check for imbalance
		
		// Left Left (LL) Case
        if (balance > 1 && data.compareTo(node.getLeft().getData()) < 0) {
            return rotateRight(node);
        }

        // Right Right (RR) Case
        if (balance < -1 && data.compareTo(node.getRight().getData()) > 0) {
            return rotateLeft(node);
        }

        // Left Right (LR) Case
        if (balance > 1 && data.compareTo(node.getLeft().getData()) > 0) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        // Right Left (RL) Case
        if (balance < -1 && data.compareTo(node.getRight().getData()) < 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }
        return node;
	}
	
	// Delete Method
	public void delete(T data) {
		if (data == null) return;
		
        this.root = deleteRecursive(this.root, data);
    }

    private AVLNode<T> deleteRecursive(AVLNode<T> root, T data) {
        if (root == null) return null;

        int comparison = data.compareTo(root.getData());

        if (comparison < 0) {
            root.setLeft(deleteRecursive(root.getLeft(), data));
            
        } else if (comparison > 0) {
            root.setRight(deleteRecursive(root.getRight(), data));
            
        } else {
        	size--;  // Target node found
            // Node to be deleted found!
            // Node with only one child or no child
            if ((root.getLeft() == null) || (root.getRight() == null)) {
                AVLNode<T> temp = (root.getLeft() != null) ? root.getLeft() : root.getRight();

                if (temp == null) {
                    root = null;  // No child case
                    
                } else {
                    root = temp;  // One child case
                }
            } else {
                // Node with two children: Get the in-order successor (smallest in right subtree)
                AVLNode<T> temp = getMinValueNode(root.getRight());
                root.setData(temp.getData());
                size++;  // Compensate for double decrement in recursive call
                root.setRight(deleteRecursive(root.getRight(), temp.getData()));
            }
        }

        if (root == null) return null;

        updateHeight(root);  // Update height
        int balance = getBalance(root);  // Check balance factor

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
    
    private AVLNode<T> getMinValueNode(AVLNode<T> node) {
        AVLNode<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }
	
    // Search Method
    public T search(T key) {
    	if (key == null) return null;
    	
        AVLNode<T> node = searchRecursive(this.root, key);
        return (node != null) ? node.getData() : null;
    }

    private AVLNode<T> searchRecursive(AVLNode<T> root, T key) {
        if (root == null) return root;
        
        int comparison = key.compareTo(root.getData());

        if (comparison == 0) {
            return root;
            
        } else if (comparison < 0) {
        	return searchRecursive(root.getLeft(), key);
        	
        } else {
        	return searchRecursive(root.getRight(), key);
        }
    }
    
    // Traversal Methods
    // In-order Traversal
    public void inorderTraversal() {
        System.out.println("\n--- Inorder Traversal ---");
        inorderRecursive(this.root);
    }

    private void inorderRecursive(AVLNode<T> node) {
        if (node != null) {
            inorderRecursive(node.getLeft());
            System.out.println(node.getData());
            System.out.println("---------------------------------");
            inorderRecursive(node.getRight());
        }
    }

    // Pre-order Traversal
    public void preorderTraversal() {
        System.out.println("\n--- Preorder Traversal ---");
        preorderRecursive(this.root);
    }

    private void preorderRecursive(AVLNode<T> node) {
        if (node != null) {
            System.out.println(node.getData());
            preorderRecursive(node.getLeft());
            preorderRecursive(node.getRight());
        }
    }

    // Post-order Traversal
    public void postorderTraversal() {
        System.out.println("\n--- Postorder Traversal ---");
        postorderRecursive(this.root);
    }

    private void postorderRecursive(AVLNode<T> node) {
        if (node != null) {
            postorderRecursive(node.getLeft());
            postorderRecursive(node.getRight());
            System.out.println(node.getData());
        }
    }
}
