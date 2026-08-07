package com.eventmgmt;

import com.eventmgmt.model.*;

import java.util.List;

public class MainApplication {
	
	public static void main(String[] args) {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("             EVENT MANAGEMENT SYSTEM - COMMAND LINE APPLICATION              ");
		System.out.println("-----------------------------------------------------------------------------");
		
		syncDataFromDatabase();
		
		boolean running = true;
		
		while(running) {
			displayMainMenu();
			
			int choice = SubMenuHandler.readIntInput("Select an Option (1-7): ");
			
			switch (choice) {
			case 1 -> SubMenuHandler.manageVenuesAndGraph();
			case 2 -> SubMenuHandler.manageEventAndAVL();
			case 3 -> SubMenuHandler.manageAttendeeAndHashing();
			case 4 -> SubMenuHandler.manageBookingAndWaitingList();
			case 5 -> SubMenuHandler.manageActivityHistory();
			case 6 -> SubMenuHandler.runSortingBenchmarking();
			case 7 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("\nExiting Event Management System. Goodbye!");
				System.out.println("-----------------------------------------------------------------------------");
				running = false;
			}
			default -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Invalid selection. Please try again.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			}
		}
	}
	
	private static void syncDataFromDatabase() {
		List<Venue> venues = SubMenuHandler.venueDAO.getAllVenues();
		for (Venue v : venues) {
			SubMenuHandler.venueGraphManager.addVenue(v);
		}
		
		List<Event> events = SubMenuHandler.eventDAO.getAllEvents();
		for (Event e : events) {
			SubMenuHandler.eventAVLManager.addEvent(e);
			SubMenuHandler.eventAVLManager.addEvent(e);
		}
		
		List<Attendee> attendees = SubMenuHandler.attendeeDAO.getAllAttendees();
		for (Attendee a : attendees) {
			SubMenuHandler.attendeeHashManager.registerAttendee(a);
			SubMenuHandler.attendeeSet.add(a);
			SubMenuHandler.attendeeManager.addAttendee(a);
		}
		
		System.out.println("Sync Complete: Loaded " + venues.size() + " venues, " + events.size() + " events, and " + attendees.size() + " attendees.");
	}
	
	private static void displayMainMenu() {
		System.out.println("\n-------------------------------- MAIN MENU ---------------------------------");
		System.out.println("1. Venue & Graph Management");
		System.out.println("2. Event & AVL Tree Management");
		System.out.println("3. Attendee & Hash Table Management");
		System.out.println("4. Booking & Waiting List Queue");
		System.out.println("5. Activity Histoy Stack");
		System.out.println("6. Algorithm Sorting & Benchmarking");
		System.out.println("7. Exit");
		System.out.println("-----------------------------------------------------------------------------");
	}
}
