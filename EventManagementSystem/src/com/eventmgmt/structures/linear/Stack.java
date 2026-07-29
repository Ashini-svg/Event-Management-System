package com.eventmgmt.structures.linear;

public class Stack {

    private static class Node {

        String data;
        Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    // Constructor
    public Stack() {
        top = null;
        size = 0;
    }

    // Push
    public void push(String item) {

        Node newNode = new Node(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop
    public String pop() {

        if (isEmpty()) {
            return null;
        }

        String item = top.data;
        top = top.next;
        size--;

        return item;
    }

    // Peek
    public String peek() {

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

        Node current = top;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}