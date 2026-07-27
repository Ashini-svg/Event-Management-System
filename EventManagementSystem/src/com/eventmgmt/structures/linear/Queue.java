package com.eventmgmt.structures.linear;

import com.eventmgmt.model.Attendee;

public class Queue {

    private static class Node {
        private Attendee data;
        private Node next;

        public Node(Attendee data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Add attendee to queue
    public void enqueue(Attendee attendee) {
        Node newNode = new Node(attendee);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Remove attendee from queue
    public Attendee dequeue() {
        if (isEmpty()) {
            return null;
        }
        Attendee data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    // View first attendee
    public Attendee peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}