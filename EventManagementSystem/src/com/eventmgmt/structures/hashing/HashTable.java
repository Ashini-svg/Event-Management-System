package com.eventmgmt.structures.hashing;

import com.eventmgmt.model.Attendee;

public class HashTable {
    private HashNode[] buckets;
    private int capacity;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new HashNode[capacity];
        this.size = 0;
    }

    public HashTable() {
        this(10);
    }

    // Bitwise and masks sign bit to prevent negative indices on Integer.MIN_VALUE
    private int getBucketIndex(String key) {
        return (key.hashCode() & 0x7FFFFFFF) % capacity;
    }

    // Insertion or update operation
    public void put(String key, Attendee value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or Value cannot be null");
        }

        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];

        // If key already exists, update the value
        while (head != null) {
            if (head.key.equalsIgnoreCase(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Insert new node at the head of the chain
        size++;
        head = buckets[bucketIndex];
        HashNode newNode = new HashNode(key, value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
    }

    // Retrieval operation
    public Attendee get(String key) {
        if (key == null) return null;

        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];

        while (head != null) {
            if (head.key.equalsIgnoreCase(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    // Deletion operation
    public Attendee remove(String key) {
        if (key == null) return null;

        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        HashNode prev = null;

        while (head != null) {
            if (head.key.equalsIgnoreCase(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) return null;

        size--;
        if (prev != null) {
            prev.next = head.next;
        } else {
            buckets[bucketIndex] = head.next;
        }

        return head.value;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}