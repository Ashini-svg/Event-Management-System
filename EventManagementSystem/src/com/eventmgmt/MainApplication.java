package com.eventmgmt;

import com.eventmgmt.database.AttendeeDAO;
import com.eventmgmt.database.EventDAO;
import com.eventmgmt.database.VenueDAO;
import com.eventmgmt.model.Attendee;
import com.eventmgmt.model.Event;
import com.eventmgmt.model.Venue;

import java.time.LocalDateTime;

public class MainApplication {
	public static void main(String[] args) {
        System.out.println("=== STARTING DATABASE INTEGRATION TEST ===\n");

        // Initialize DAOs
        VenueDAO venueDAO = new VenueDAO();
        AttendeeDAO attendeeDAO = new AttendeeDAO();
        EventDAO eventDAO = new EventDAO();

        // Unique Test IDs to avoid collisions
        String testVenueId = "TEST_V001";
        String testAttendeeId = "TEST_A001";
        String testEventId = "TEST_E001";

        // Create Test Objects
        Venue testVenue = new Venue(testVenueId, "Test Grand Hall", "Building Z", 300, true);
        Attendee testAttendee = new Attendee(testAttendeeId, "John Tester", "john.test@example.com");
        Event testEvent = new Event(testEventId, "Tech Conference 2026", "Technology", testVenueId, LocalDateTime.now().plusDays(10), 300);

        // STEP 1: INSERT DATA
        System.out.println("--- 1. INSERTING TEST DATA ---");

        // Note: Insert Venue first because Event references Venue ID as a Foreign Key!
        boolean venueInserted = venueDAO.saveVenue(testVenue);
        System.out.println("Venue Saved: " + (venueInserted ? "SUCCESS" : "FAILED"));

        boolean attendeeInserted = attendeeDAO.saveAttendee(testAttendee);
        System.out.println("Attendee Saved: " + (attendeeInserted ? "SUCCESS" : "FAILED"));

        boolean eventInserted = eventDAO.saveEvent(testEvent);
        System.out.println("Event Saved: " + (eventInserted ? "SUCCESS" : "FAILED"));

        // STEP 2: VERIFY INSERTIONS
        System.out.println("\n--- 2. VERIFYING INSERTED DATA ---");
        System.out.println("Total Venues in DB: " + venueDAO.getAllVenues().size());
        System.out.println("Total Attendees in DB: " + attendeeDAO.getAllAttendees().size());
        System.out.println("Total Events in DB: " + eventDAO.getAllEvents().size());

        // STEP 3: CLEANUP / DELETE INSERTED DATA
        System.out.println("\n--- 3. CLEANING UP (DELETING TEST DATA) ---");

        // Note: Delete Event first before deleting Venue due to Foreign Key constraints!
        boolean eventDeleted = eventDAO.deleteEvent(testEventId);
        System.out.println("Test Event Deleted: " + (eventDeleted ? "SUCCESS" : "FAILED"));

        boolean attendeeDeleted = attendeeDAO.deleteAttendee(testAttendeeId);
        System.out.println("Test Attendee Deleted: " + (attendeeDeleted ? "SUCCESS" : "FAILED"));

        boolean venueDeleted = venueDAO.deleteVenue(testVenueId);
        System.out.println("Test Venue Deleted: " + (venueDeleted ? "SUCCESS" : "FAILED"));

        System.out.println("\n=== INTEGRATION TEST COMPLETE ===");
        
    }
}
