package com.eventmgmt.structures.linear;

public class Stack<T> {

    private static class Node<T> {

        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> top;
    private int size;

    
    // Constructor
    public Stack() {
        top = null;
        size = 0;
    }

    
    // Push
    public void push(T item) {

        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    
    // Pop
    public T pop() {

        if (isEmpty()) {
            return null;
        }

        T item = top.data;
        top = top.next;
        size--;

        return item;
    }

    
    // Peek
    public T peek() {

        if (isEmpty()) {
            return null;
        }

        return top.data;
    }

   
    // Check Empty
    public boolean isEmpty() {
        return top == null;
    }

   
    // Get Size
    public int getSize() {
        return size;
    }

    
    // Display Stack
    public void display() {

        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }

        Node<T> current = top;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}