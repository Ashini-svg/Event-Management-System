package com.eventmgmt.structures.linear;


public class Queue<T> {

    // Node class
    private static class Node<T> {

        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front;
    private Node<T> rear;
    private int size;

    
    // Constructor
    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    
    // Enqueue
    public void enqueue(T item) {

        Node<T> newNode = new Node<>(item);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
    }

    
    // Dequeue
    public T dequeue() {

        if (isEmpty()) {
            return null;
        }

        T item = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;

        return item;
    }

    
    // Peek
    public T peek() {

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

        Node<T> current = front;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}