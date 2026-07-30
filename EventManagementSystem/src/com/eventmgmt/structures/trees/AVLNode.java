package com.eventmgmt.structures.trees;

public class AVLNode<T extends Comparable<T>> {
	private T data;
	private AVLNode<T> left;
	private AVLNode<T> right;
	private int height;
	
	public AVLNode(T data) {
		this. data = data;
		this.left = null;
		this.right = null;
		this.height = 1;  // New nodes start with height 1
	}
	
	// Getters
	public T getData() {
		return data;
	}
	
	public AVLNode<T> getLeft() {
		return left;
	}
	
	public AVLNode<T> getRight() {
		return right;
	}
	
	public int getHeight() {
		return height;
	}
	
	// Setters
	public void setData(T data) {
		this.data = data;
	}
	
	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}
	
	public void setRight(AVLNode<T> right) {
		this.right = right;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
}
