package com.eventmgmt.structures.hashing;

public class SetADT<T> {
    
    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T>[] buckets;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public SetADT(int capacity) {
        this.capacity = capacity;
        this.buckets = new Node[capacity];
        this.size = 0;
    }

    public SetADT() {
        this(10);
    }

    private int getIndex(T element) {
        return Math.abs(element.hashCode()) % capacity;
    }

    // Add element while preventing duplicates
    public boolean add(T element) {
        if (element == null) return false;

        if (contains(element)) {
            System.out.println("Duplicate entry detected! Element '" + element + "' was not added.");
            return false; // Duplicate prevented
        }

        int index = getIndex(element);
        Node<T> newNode = new Node<>(element);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        return true;
    }

    // Check if element exists in the Set
    public boolean contains(T element) {
        if (element == null) return false;

        int index = getIndex(element);
        Node<T> current = buckets[index];

        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
}
