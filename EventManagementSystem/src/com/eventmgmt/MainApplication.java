package com.eventmgmt;

import com.eventmgmt.algorithms.*;
import com.eventmgmt.database.*;
import com.eventmgmt.model.*;
import com.eventmgmt.structures.graphs.*;
import com.eventmgmt.structures.hashing.*;
import com.eventmgmt.structures.linear.*;
import com.eventmgmt.structures.trees.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
	
	private static final Scanner scanner = new Scanner(System.in);
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	// Database DAOs
	private static final VenueDAO venueDAO = new VenueDAO();
	private static final EventDAO eventDAO = new EventDAO();
	private static final AttendeeDAO attendeeDAO = new AttendeeDAO();
	private static final BookingDAO bookingDAO = new BookingDAO();
	
	// In-Memory Data Structure Managers
	private static final VenueGraphManager venueGraphManager = new VenueGraphManager();
	private static final EventAVLManager eventAVLManager = new EventAVLManager();
	private static final AttendeeHashManager attendeeHashManager = new AttendeeHashManager();
	private static final SetADT attendeeSet = new SetADT();
	private static final WaitingListManager waitingListManager = new WaitingListManager();
	private static final ActivityHistory activityHistory = new ActivityHistory();
	private static final CategoryArray categoryArray = new CategoryArray();
	
	public static void main(String[] args) {
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("                 EVENT MANAGEMENT SYSTEM - INITIALIZING DATA                ");
		System.out.println("----------------------------------------------------------------------------");
		
		// Pre-load data from MySQL into custom data structures
		syncDataFromDatabase();
		
		boolean running = true;
		
		
	}
	
	// Syncs MySQL database records into in-memory data structures
	private static void syncDataFromDatabase() {
		List<Venue> venues = venueDAO.getAllVenues();
		for (Venue v : venues) {
			venueGraphManager.addVenue(v);
		}
		
		List<Event> events = eventDAO.getAllEvents();
		for (Event e : events) {
			eventAVLManager.addEvent(e);
		}
		
		List<Attendee> attendees = attendeeDAO.getAllAttendees();
		for (Attendee a : attendees) {
			attendeeHashManager.registerAttendee(a);
			attendeeSet.add(a);
		}
		
		System.out.println("Sync Complete: Loaded " + venues.size() + "venues, " + events.size() + "events, and " + attendees.size() + " attendees.");
	}
	
	private static void displayMainMenu() {
		System.out.println("\n------------------------------ MAIN MENU --------------------------------");
		System.out.println("1. Venue Management & Graph Traversals");
		System.out.println("2. Event Management & AVL Tree Operations");
		System.out.println("3. Attendee Management & Hash Table");
		System.out.println("4. Bookings & Waiting List Queue");
		System.out.println("5. Activity History & Undo System");
		System.out.println("6. Run Algorithm Benchmarks & Sorting");
		System.out.println("7. Exit");
		System.out.println("----------------------------------------------------------------------------");
	}
}
