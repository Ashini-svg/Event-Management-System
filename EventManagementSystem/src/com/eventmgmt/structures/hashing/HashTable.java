package com.eventmgmt.structures.hashing;

import com.eventmgmt.model.Attendee;

public class HashTable {
    
    // Hash Node for Separate Chaining collision handling
    private static class HashNode {
        String key; // e.g., attendeeId or email
        Attendee value;
        HashNode next;

        public HashNode(String key, Attendee value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private HashNode[] buckets;
    private int capacity;
    private int size;
    private static final double LOAD_FACTOR = 0.75;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new HashNode[capacity];
        this.size = 0;
    }

    public HashTable() {
        this(10); // Default capacity
    }

    // Custom Hash Function
    private int getBucketIndex(String key) {
        return Math.abs(key.toLowerCase().hashCode()) % capacity;
    }

    // Insert or Update Key-Value pair
    public void put(String key, Attendee value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or Value cannot be null");
        }
        
        if ((double) size / capacity >= LOAD_FACTOR) {
        	resize();
        }

        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];

        // Check if key already exists (Update)
        while (head != null) {
            if (head.key.equalsIgnoreCase(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Insert new node at the head (Collision Resolution via Chaining)
        size++;
        head = buckets[bucketIndex];
        HashNode newNode = new HashNode(key, value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
    }
    
    private void resize() {
    	int oldCapacity = capacity;
    	HashNode[] oldBuckets = buckets;
    	
    	capacity = oldCapacity * 2;
    	buckets = new HashNode[capacity];
    	size = 0;
    	
    	for (int i = 0; i < oldCapacity; i++) {
    		HashNode head = oldBuckets[i];
    		while (head != null) {
    			put(head.key, head.value);
    			head = head.next;
    		}
    	}
    }

    // Retrieve element by Key
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

        return null; // Key not found
    }
   
    // Remove element by Key
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