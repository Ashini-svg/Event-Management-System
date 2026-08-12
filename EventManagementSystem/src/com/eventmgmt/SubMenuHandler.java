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
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class SubMenuHandler {
	
	public static final Scanner scanner  = new Scanner(System.in);
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	// Database DAOs
	public static final VenueDAO venueDAO = new VenueDAO();
	public static final EventDAO eventDAO = new EventDAO();
	public static final AttendeeDAO attendeeDAO = new AttendeeDAO();
	public static final BookingDAO bookingDAO = new BookingDAO();
	
	// In-Memory Data Structure Managers
	public static final VenueGraphManager venueGraphManager = new VenueGraphManager();
	public static final EventAVLManager eventAVLManager = new EventAVLManager();
	public static final EventBSTManager eventBSTManager = new EventBSTManager();
	public static final AttendeeHashManager attendeeHashManager = new AttendeeHashManager();
	public static final AttendeeManager attendeeManager = new AttendeeManager();
	public static final SetADT attendeeSet = new SetADT();
	public static final WaitingListManager waitingListManager = new WaitingListManager();
	public static final ActivityHistory activityHistory = new ActivityHistory();
	public static final CategoryArray categoryArray = new CategoryArray();
	
	// Helper method for safe integer input reading
	public static int readIntInput(String prompt) {
		System.out.print(prompt);
		
		while (!scanner.hasNextInt()) {
			System.out.print("Invalid input. please enter a number: ");
			scanner.next();
		}
		int result = scanner.nextInt();
		scanner.nextLine();
		return result;
	}
	
	// Helper method for safe LocalDateTime user input
	public static LocalDateTime readDateTimeInput(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			try {
				return LocalDateTime.parse(input, FORMATTER);
				
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date-time format. Example format: 2026-10-15 18:30");
			}
		}
	}
	
	// Null-safe list-to-array helper
	public static <T> T[] safeToArray(List<T> list, T[] emptyArray) {
		if (list == null) {
			return emptyArray;
		}
		return list.toArray(emptyArray);
	}
	
	// 1. Venue & Graph Management sub-menu
	public static void manageVenuesAndGraph() {
		boolean back = false;
		
		while (!back) {
			System.out.println("\n------------------------- VENUE & GRAPH MANAGEMENT -------------------------");
			System.out.println("1. Add New Venue");
			System.out.println("2. Connect Two Venues (Add Edge)");
			System.out.println("3. Display Venue Graph (Adjacency List)");
			System.out.println("4. Perform Breadth-First Search (BFS)");
			System.out.println("5. Perform Depth-First Search (DFS)");
			System.out.println("6. Back to Main Menu");
			System.out.println("-----------------------------------------------------------------------------");
			
			int choice = readIntInput("Choice: ");
			
			switch (choice) {
			case 1 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Venue ID: ");
				String id =  scanner.nextLine();
				System.out.print("Enter Venue Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter Location: ");
				String location = scanner.nextLine();
				int capacity =  readIntInput("Enter Capacity: ");
				
				Venue venue = new Venue(id, name, location, capacity, true);
				
				if (venueDAO.saveVenue(venue)) {
					venueGraphManager.addVenue(venue);
					
					activityHistory.logActivity("added venue: " + id, () -> {
						venueDAO.deleteVenue(id);
						venueGraphManager.getGraph().removeNode(id);
					});
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Venue added successfully!");
					System.out.println("-----------------------------------------------------------------------------");
				}
			}
			case 2 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter First Venue ID: ");
				String v1 = scanner.nextLine();
				System.out.print("Enter Second Venue ID: ");
				String v2 = scanner.nextLine();
				
				if (venueGraphManager.getGraph().findNode(v1) == null || venueGraphManager.getGraph().findNode(v2) == null) {
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Connection Failed: one or both Venue IDs were not found.");
					System.out.println("-----------------------------------------------------------------------------");
					break;
				}
				if (v1.equalsIgnoreCase(v2)) {
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Connection Failed: cannot connect a venue to itself.");
					System.out.println("-----------------------------------------------------------------------------");
					break;
				}
				
				venueGraphManager.connectVenues(v1, v2);
				activityHistory.logActivity("connected venues: " + v1 + " and " + v2, () -> {
					venueGraphManager.getGraph().removeConnection(v1, v2);
				});
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Venues connected successfully!");
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 3 -> {
				System.out.println("-----------------------------------------------------------------------------");
				venueGraphManager.displayGraph();
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 4 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Starting Venue ID for BFS: ");
				venueGraphManager.performBFS(scanner.nextLine());
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 5 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Starting Venue ID for DFS: ");
				venueGraphManager.performDFS(scanner.nextLine());
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 6 -> {
				System.out.println("-----------------------------------------------------------------------------");
				back = true;
			}
			default -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Invalid selection. Please try again.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			}
		}
	}
	
	// 2. Event & AVL Tree Management sub-menu
	public static void manageEventAndAVL() {
		boolean back = false;
		
		while (!back) {
			System.out.println("\n----------------------- EVENT & AVL TREE MANAGEMENT ------------------------");
			System.out.println("1. Add New Event");
			System.out.println("2. Search Event by ID (AVL Search)");
			System.out.println("3. Search Event by ID (BST Search)");
			System.out.println("4. Remove Event by ID");
			System.out.println("5. Display All Events - AVL (In-order Traversal)");
			System.out.println("6. Display All Events - BST (In-order/Pre-order/Post-order)");
			System.out.println("7. Linear Search Event (by ID or Title)");
			System.out.println("8. Back to Main Menu");
			System.out.println("-----------------------------------------------------------------------------");
			
			int choice = readIntInput("Choice: ");
			
			switch (choice) {
			case 1 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Event ID: ");
				String id = scanner.nextLine();
				System.out.print("Enter Event Title: ");
				String title = scanner.nextLine();
				
				categoryArray.displayCategories();
				int catChoice = readIntInput("Select Category Index: ") - 1;
				String category = categoryArray.getCategory(catChoice);
				if (category == null) category = "General";
				
				System.out.print("Enter Venue ID: ");
				String venueId = scanner.nextLine();
				LocalDateTime eventDateTime = readDateTimeInput("Enter Event Date & Time: (yyyy-MM-dd HH:mm): ");
				int capacity = readIntInput("Enter Event Capacity: ");
				
				Event event = new Event(id, title, category, venueId, eventDateTime, capacity);
				
				if (eventDAO.saveEvent(event)) {
					eventAVLManager.addEvent(event);
					eventBSTManager.addEvent(event);
					
					activityHistory.logActivity("added event: " + id, () -> {
						eventDAO.deleteEvent(id);
						eventAVLManager.removeEvent(id);
						eventBSTManager.deleteEvent(id);
					});
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Event added successfully!");
					System.out.println("-----------------------------------------------------------------------------");
				}
			}
			case 2 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Event ID to search: ");
				Event found = eventAVLManager.getEventById(scanner.nextLine());
				System.out.println(found != null ? found : "Event not found.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 3 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Event ID to search: ");
				Event found = eventBSTManager.searchEvent(scanner.nextLine());
				System.out.println(found != null ? found : "Event not found.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 4 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Event ID to remove: ");
				String id = scanner.nextLine();
				Event existingEvent = eventAVLManager.getEventById(id);
				
				if (eventDAO.deleteEvent(id)) {
					eventAVLManager.removeEvent(id);
					eventBSTManager.deleteEvent(id);
					
					activityHistory.logActivity("removed event: " + id,() -> {
						if (existingEvent != null) {
							eventDAO.saveEvent(existingEvent);
							eventAVLManager.addEvent(existingEvent);
							eventBSTManager.addEvent(existingEvent);
						}
					});
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Event removed successfully!");
					System.out.println("-----------------------------------------------------------------------------");
					
				} else {
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Event not found. Removal failed.");
					System.out.println("-----------------------------------------------------------------------------");
				}
			}
			case 5 -> {
				System.out.println("-----------------------------------------------------------------------------");
				eventAVLManager.displayAllEvents();
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 6 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("\nIn-order:\n");
				eventBSTManager.displayInOrder();
				System.out.println("\nPre-order:\n");
				eventBSTManager.displayPreOrder();
				System.out.println("\nPost-order:\n");
				eventBSTManager.displayPostOrder();
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 7 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Search by:");
				System.out.println("1. Event ID");
				System.out.println("2. Title");
				System.out.println("-----------------------------------------------------------------------------");
				
				int mode = readIntInput("Choice: ");
				Event[] events = safeToArray(eventDAO.getAllEvents(), new Event[0]);
				
				if (mode == 1) {
					System.out.print("Enter Event ID: ");
					Event found = LinearSearch.searchEventById(events, scanner.nextLine());
					System.out.println(found != null ? found : "Event not found.");
					
				} else {
					System.out.print("Enter Event Title: ");
					Event found = LinearSearch.searchEventByTitle(events, scanner.nextLine());
					System.out.println(found != null ? found : "Event not found.");
				}
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 8 -> {
				System.out.println("-----------------------------------------------------------------------------");
				back = true;
			}
			default -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Invalid selection. Please try again.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			}
		}
	}
	
	// 3. Attendee & Hash Table Management sub-menu
	public static void manageAttendeeAndHashing() {
		boolean back = false;
		
		while (!back) {
			System.out.println("\n--------------------- ATTENDEE & HASH TABLE MANAGEMENT ---------------------");
			System.out.println("1. Register New Attendee (Set ADT Duplicate Check)");
			System.out.println("2. Search Attendee by ID (Hash Table)");
			System.out.println("3. Search Attendee by ID (Linked List)");
			System.out.println("4. Update Attendee (Linked List)");
			System.out.println("5. Remove Attendee");
			System.out.println("6. Display All Attendees (Linked List)");
			System.out.println("7. Linear Search Attendee (by ID or Name)");
			System.out.println("8. Back to Main Menu");
			System.out.println("-----------------------------------------------------------------------------");
			
			int choice = readIntInput("Choice: ");
			switch (choice) {
			case 1 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Attendee ID: ");
				String id = scanner.nextLine();
				System.out.print("Enter Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter Email: ");
				String email = scanner.nextLine();
				
				Attendee attendee = new Attendee(id, name, email);
				
				boolean emailTaken = false;
				ListNode current = attendeeManager.getAttendeeList().getHead();
				
				while (current != null) {
					if (current.getAttendee().getEmail() != null && current.getAttendee().getEmail().equalsIgnoreCase(email)) {
						emailTaken = true;
						break;
					}
					current = current.getNext();
				}
				if (emailTaken) {
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Registration blocked: Email already registered to another attendee.");
					System.out.println("-----------------------------------------------------------------------------");
					break;
				}
				if (!attendeeSet.add(attendee)) {
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Registration blocked: Attendee ID or Email already registered.");
					System.out.println("-----------------------------------------------------------------------------");
					break;
				}
				if (attendeeDAO.saveAttendee(attendee)) {
					attendeeHashManager.registerAttendee(attendee);
					attendeeManager.addAttendee(attendee);
					
					activityHistory.logActivity("registered attendee: " + id, () -> {
						attendeeDAO.deleteAttendee(id);
						attendeeHashManager.removeAttendee(id);
						attendeeSet.remove(id);
						attendeeManager.deleteAttendee(id);
					});
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Attendee registered successfully!");
					System.out.println("-----------------------------------------------------------------------------");
					
				} else {
					attendeeSet.remove(id);
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Failed to save attendee to database. Registration rolled back.");
					System.out.println("-----------------------------------------------------------------------------");
				}
			}
			case 2 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Attendee ID: ");
				Attendee found = attendeeHashManager.searchAttendee(scanner.nextLine());
				System.out.println(found != null ? found : "Attendee not found.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 3 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Attendee ID: ");
				Attendee found = attendeeManager.searchAttendee(scanner.nextLine());
				System.out.println(found != null ? found : "Attendee not found.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 4 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Attendee ID to update: ");
				String id = scanner.nextLine();
				Attendee before = attendeeManager.searchAttendee(id);
				String oldName = (before != null) ? before.getName() : null;
				String oldEmail = (before != null) ? before.getEmail() : null;
				
				System.out.print("Enter new Name: ");
				String newName = scanner.nextLine();
				System.out.print("Enter new Email: ");
				String newEmail = scanner.nextLine();
				
				boolean updated = attendeeManager.updateAttendee(id, newName, newEmail);
				
				if (updated) {
					Attendee updatedAttendee = attendeeManager.searchAttendee(id);
					attendeeDAO.saveAttendee(updatedAttendee);
					attendeeHashManager.registerAttendee(updatedAttendee);
					
					activityHistory.logActivity("updated attendee: " + id, () -> {
						if (oldName != null) {
							attendeeManager.updateAttendee(id, oldName, oldEmail);
							Attendee reverted = attendeeManager.searchAttendee(id);
							attendeeDAO.saveAttendee(reverted);
							attendeeHashManager.registerAttendee(reverted);
						}
					});
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Attendee updated successfully.");
					System.out.println("-----------------------------------------------------------------------------");
					
				} else {
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Attendee not found.");
					System.out.println("-----------------------------------------------------------------------------");
				}
				
			}
			case 5 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Attendee ID to remove: ");
				String id = scanner.nextLine();
				Attendee existingAttendee = attendeeManager.searchAttendee(id);
				
				if (attendeeDAO.deleteAttendee(id)) {
					attendeeHashManager.removeAttendee(id);
					attendeeSet.remove(id);
					attendeeManager.deleteAttendee(id);
					
					activityHistory.logActivity("removed attendee: " + id, () -> {
						if (existingAttendee != null) {
							attendeeDAO.saveAttendee(existingAttendee);
							attendeeHashManager.registerAttendee(existingAttendee);
							attendeeSet.add(existingAttendee);
							attendeeManager.addAttendee(existingAttendee);
						}
					});
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Attendee removed successfully!");
					System.out.println("-----------------------------------------------------------------------------");
					
				} else {
					System.out.println("-----------------------------------------------------------------------------");
					System.out.println("Attendee not found. Removal failed.");
					System.out.println("-----------------------------------------------------------------------------");
				}
			}
			case 6 -> {
				System.out.println("-----------------------------------------------------------------------------");
				attendeeManager.displayAttendees();
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 7 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Search by:");
				System.out.println("1. Attendee ID");
				System.out.println("2. Attendee Name");
				
				int mode = readIntInput("Choice: ");
				Attendee[] attendees = safeToArray(attendeeDAO.getAllAttendees(), new Attendee[0]);
				
				if (mode == 1) {
					System.out.print("Enter Attendee ID: ");
					Attendee found = LinearSearch.searchAttendeeById(attendees, scanner.nextLine());
					System.out.println(found != null ? found : "Attendee not found.");
					
				} else {
					System.out.print("Enter Attendee Name: ");
					Attendee found = LinearSearch.searchAttendeeByName(attendees, scanner.nextLine());
					System.out.println(found != null ? found : "Attendee not found.");
				}
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 8 -> {
				System.out.println("-----------------------------------------------------------------------------");
				back = true;
			}
			default -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Invalid selection. Please try again.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			}
		}
	}
	
	// 4. Booking & Waiting List Queue sub-menu
	public static void manageBookingAndWaitingList() {
		boolean back = false;
		
		while (!back) {
			System.out.println("\n----------------------- BOOKING & WAITING LIST QUEUE -----------------------");
			System.out.println("1. Book Ticket for Event");
			System.out.println("2. Process Next Attendee in Waiting List");
			System.out.println("3. View Waiting List Status");
			System.out.println("4. Back to Main Menu");
			System.out.println("-----------------------------------------------------------------------------");
			
			int choice = readIntInput("Choice: ");
			
			switch (choice) {
			case 1 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.print("Enter Event ID: ");
				String eventId = scanner.nextLine();
				Event event = eventAVLManager.getEventById(eventId);
				
				if (event == null) {
					System.out.println("Event not found.");
					System.out.println("-----------------------------------------------------------------------------");
					break;
				}
				System.out.print("Enter Attendee ID: ");
				String attendeeId = scanner.nextLine();
				Attendee attendee = attendeeHashManager.searchAttendee(attendeeId);
				
				if (attendee == null) {
					System.out.println("Attendee not found. Attendee must register first.");
					System.out.println("-----------------------------------------------------------------------------");
					break;
				}
				
				if (event.isFull()) {
					System.out.println("Event is full! Adding attendee to Waiting List Queue...");
					System.out.println("-----------------------------------------------------------------------------");
					waitingListManager.addToWaitingList(eventId, attendee);
					
					activityHistory.logActivity("added " + attendeeId + " to waiting list for " + eventId, () -> {
						waitingListManager.removeFromWaitingList(eventId, attendeeId);
					});
					
				} else {
					String bookingId = "B-" + System.currentTimeMillis();
					Booking booking = new Booking(bookingId, eventId, attendeeId, LocalDateTime.now());
					
					if (bookingDAO.addBooking(booking)) {
						event.incrementAttendees();
						eventDAO.saveEvent(event);
						
						activityHistory.logActivity("created booking: " + bookingId, () -> {
							bookingDAO.deleteBooking(bookingId);
							event.decrementAttendees();
							eventDAO.saveEvent(event);
						});
						System.out.println("-----------------------------------------------------------------------------");
						System.out.println("Booking successful! ID: " + bookingId);
						System.out.println("-----------------------------------------------------------------------------");
						
					} else {
						System.out.println("-----------------------------------------------------------------------------");
						System.out.println("Booking failed. Please try again.");
						System.out.println("-----------------------------------------------------------------------------");
					}
				}
			}
			case 2 -> {
				System.out.println("-----------------------------------------------------------------------------");
				if (waitingListManager.getWaitingCount() == 0) {
					System.out.println("Waiting List is empty.");
					System.out.println("-----------------------------------------------------------------------------");
					break;
				}
				System.out.print("Enter Event ID the freed-up slot is for: ");
				String eventId = scanner.nextLine();
				Event event = eventAVLManager.getEventById(eventId);
				
				if (event == null) {
					System.out.println("Event not found.");
					System.out.println("-----------------------------------------------------------------------------");
					break;
				}
				if (event.isFull()) {
					System.out.println("That event still has no free slots.");
					System.out.println("-----------------------------------------------------------------------------");
					break;
				}
				Attendee next = waitingListManager.processNextAttendeeForEvent(eventId);
				
				if (next != null) {
					String bookingId = "B-" + System.currentTimeMillis();
					Booking booking = new Booking(bookingId, eventId, next.getAttendeeId(), LocalDateTime.now());
					
					if (bookingDAO.addBooking(booking)) {
						event.incrementAttendees();
						eventDAO.saveEvent(event);
						
						activityHistory.logActivity("processed waiting list attendee: " + next.getAttendeeId() + " into booking: " + bookingId, () -> {
							bookingDAO.deleteBooking(bookingId);
							event.decrementAttendees();
							eventDAO.saveEvent(event);
							waitingListManager.addToWaitingList(eventId, next);
						});
						System.out.println("Booking created for " + next.getName() + "! ID: " + bookingId);
						
					} else {
						waitingListManager.addToWaitingList(eventId, next);
						System.out.println("Booking failed. Please try again.");
					}
				}
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 3 -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Total Waiting Count: " + waitingListManager.getWaitingCount());
				waitingListManager.displayWaitingList();
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 4 -> {
				System.out.println("-----------------------------------------------------------------------------");
				back = true;
			}
			default -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Invalid selection. Please try again.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			}
		}
	}
	
	// 5. Activity History Stack sub-menu
	public static void  manageActivityHistory() {
		boolean back = false;
		
		while (!back) {
			System.out.println("\n-------------------------- ACTIVITY HISTORY STACK --------------------------");
			System.out.println("1. View Latest Activity");
			System.out.println("2. Display Full History Log");
			System.out.println("3. Undo Latest Activity");
			System.out.println("4. Back to Main Menu");
			System.out.println("-----------------------------------------------------------------------------");
			
			int choice = readIntInput("Choice: ");
			
			switch (choice) {
			case 1 -> {
				System.out.println("-----------------------------------------------------------------------------");
				activityHistory.viewLatestActivity();
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 2 -> {
				System.out.println("-----------------------------------------------------------------------------");
				activityHistory.displayActivities();
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 3 -> {
				System.out.println("-----------------------------------------------------------------------------");
				activityHistory.undoLastActivity();
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 4 -> {
				System.out.println("-----------------------------------------------------------------------------");
				back = true;
			}
			default -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Invalid selection. Please try again.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			}
		}
	}
	
	// 6. Sorting & Benchmarking sub-menu
	public static void runSortingBenchmarking() {
		boolean back = false;
		
		while (!back) {
			System.out.println("\n--------------------- ALGORITHM SORTING & BENCHMARKING ---------------------");
			System.out.println("1. Sort Venues using Merge Sort");
			System.out.println("2. Sort Attendee using Quick Sort");
			System.out.println("3. Sort Venues using Selection Sort");
			System.out.println("4. Sort Events using Insertion Sort");
			System.out.println("5. Sort Attendees using Bubble Sort");
			System.out.println("6. Back to Main Menu");
			System.out.println("-----------------------------------------------------------------------------");
			
			int choice = readIntInput("Choice: ");
			
			switch (choice) {
			case 1 -> {
				Venue[] venues = safeToArray(venueDAO.getAllVenues(), new Venue[0]);
				new MergeSort().sort(venues);
				
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Venues sorted via Merge Sort: ");
				for (Venue v : venues) {
					if (v != null) System.out.println(" - " + v.getVenueName());
				}
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 2 -> {
				Attendee[] attendees = safeToArray(attendeeDAO.getAllAttendees(), new Attendee[0]);
				if (attendees.length > 0) {
					QuickSort.sort(attendees, 0, attendees.length - 1);
				}
				
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Attendees sorted via Quick Sort (by ID): ");
				for (Attendee a : attendees) {
					if (a != null) System.out.println(" - " + a.getAttendeeId() + ": " + a.getName());
				}
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 3 -> {
				Venue[] venues = safeToArray(venueDAO.getAllVenues(), new Venue[0]);
				SelectionSort.sort(venues);
				
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Venues sorted via Selection Sort: ");
				for (Venue v : venues) {
					if (v != null) System.out.println(" - " + v.getVenueName());
				}
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 4 -> {
				Event[] events = safeToArray(eventDAO.getAllEvents(), new Event[0]);
				InsertionSort.sort(events);
				
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Events sorted via Insertion Sort (by ID): ");
				for (Event e : events) {
					if (e != null) System.out.println(" - " + e.getEventId() + ": " + e.getTitle());
				}
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 5 -> {
				Attendee[] attendees = safeToArray(attendeeDAO.getAllAttendees(), new Attendee[0]);
				new BubbleSort().sortByName(attendees);
				
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Attendees sorted via Bubble Sort (by Name): ");
				for (Attendee a : attendees) {
					if (a != null) System.out.println(" - " + a.getName() + " (" + a.getAttendeeId() + ")");
				}
				System.out.println("-----------------------------------------------------------------------------");
			}
			case 6 -> {
				System.out.println("-----------------------------------------------------------------------------");
				back = true;
			}
			default -> {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Invalid selection. Please try again.");
				System.out.println("-----------------------------------------------------------------------------");
			}
			}
		}
	}
}
