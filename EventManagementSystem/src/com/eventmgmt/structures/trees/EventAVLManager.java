package com.eventmgmt.structures.trees;

import com.eventmgmt.model.Event;

public class EventAVLManager {
	private final AVLTree<Event> eventTree;
	
	public EventAVLManager() {
		this.eventTree = new AVLTree<>();
	}
	
	// Inserts an event into the balanced AVL tree
	public void addEvent(Event event) {
		if (event != null && event.getEventId() != null) {
			eventTree.insert(event);
		}
	}
	
	// Searches for an event using a dummy key containing the target event ID
	public Event getEventById(String eventId) {
		if (eventId == null || eventId.trim().isEmpty()) {
			return null;
		}
		
		Event searchKey = new Event();
		searchKey.setEventId(eventId);
		return eventTree.search(searchKey);
	}
	
	// Deletes an event by ID and re-balances the tree
	public boolean removeEvent(String eventId) {
		Event existingEvent = getEventById(eventId);
		if (existingEvent != null) {
			eventTree.delete(existingEvent);
			return true;
		}
		return false;
	}
	
	public void displayAllEvents() {
		if (eventTree.isEmpty()) {
			System.out.println("No events registered in the system.");
			return;
		}
		eventTree.inorderTraversal();
	}
	
	public int getTotalEvents() {
		return eventTree.getSize();
	}
	
	public int getTreeHeight() {
		return eventTree.getTreeHeight();
	}
	
	public AVLTree<Event> getRawTree() {
		return this.eventTree;
	}
}
