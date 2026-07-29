package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class Queue {

    // Node class
    private static class Node {

        Attendee data;
        Node next;

        Node(Attendee data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    // Constructor
    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Enqueue
    public void enqueue(Attendee attendee) {

        Node newNode = new Node(attendee);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    // Dequeue
    public Attendee dequeue() {

        if (isEmpty()) {
            return null;
        }

        Attendee attendee = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;

        return attendee;
    }

    // Peek
    public Attendee peek() {

        if (isEmpty()) {
            return null;
        }

        return front.data;
    }

    // Empty
    public boolean isEmpty() {
        return front == null;
    }

    // Size
    public int getSize() {
        return size;
    }

    // Display
    public void display() {

        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        Node current = front;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}