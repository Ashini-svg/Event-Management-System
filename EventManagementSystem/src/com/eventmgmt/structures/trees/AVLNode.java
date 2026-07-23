package com.eventmgmt.structures.trees;

import com.eventmgmt.model.Attendee;

public class AVLNode {
	private Attendee attendee;
	private AVLNode left;
	private AVLNode right;
	private int height;
	
	public AVLNode(Attendee attendee) {
		this.attendee = attendee;
		this.left = null;
		this.right = null;
		this.height = 1;  // New nodes start with height 1
	}
	
	// Getters
	public Attendee getAttendee() {
		return attendee;
	}
	
	public AVLNode getLeft() {
		return left;
	}
	
	public AVLNode getRight() {
		return right;
	}
	
	public int getHeight() {
		return height;
	}
	
	// Setters
	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
	}
	
	public void setLeft(AVLNode left) {
		this.left = left;
	}
	
	public void setRight(AVLNode right) {
		this.right = right;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
}
