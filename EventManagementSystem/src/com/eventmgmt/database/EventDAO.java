package com.eventmgmt.database;

import com.eventmgmt.model.Event;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
	// Inserts a new event rocord or updates an existing one
	public boolean saveEvent(Event event) {
		if (event == null) return false;
		
		String sql = "INSERT INTO events (event_id, title, category, venue_id, event_datetime, capacity, attendees_count) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE title = ?, category = ?, venue_id = ?, event_datetime = ?, capacity = ?, attendees_count = ?";
		
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// INSERT values
			pstmt.setString(1, event.getEventId());
			pstmt.setString(2, event.getTitle());
			pstmt.setString(3, event.getCategory());
			pstmt.setString(4, event.getVenueId());
			pstmt.setTimestamp(5, Timestamp.valueOf(event.getEventDateTime()));
			pstmt.setInt(6, event.getCapacity());
			pstmt.setInt(7, event.getAttendeesCount());
			
			// UPDATE fallback values
			pstmt.setString(8, event.getTitle());
            pstmt.setString(9, event.getCategory());
            pstmt.setString(10, event.getVenueId());
            pstmt.setTimestamp(11, Timestamp.valueOf(event.getEventDateTime()));
            pstmt.setInt(12, event.getCapacity());
            pstmt.setInt(13, event.getAttendeesCount());
			
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.err.println("Error saving event: " + e.getMessage());
            e.printStackTrace();
            return false;
		}
	}
	
	// Retrieves all event records from the database
	public List<Event> getAllEvents() {
		List<Event> events = new ArrayList<>();
		String sql = "SELECT * FROM events";
		
		try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				String eventId = rs.getString("event_id");
				String title = rs.getString("title");
				String category = rs.getString("venue_id");
				String venueId = rs.getString("venue_id");
				LocalDateTime dateTime = rs.getTimestamp("event_datetime").toLocalDateTime();
				int capacity = rs.getInt("capacity");
				int attendeesCount = rs.getInt("attendees_count");
				
				Event event = new Event(eventId, title, category, venueId, dateTime, capacity);
				event.setAttendeesCount(attendeesCount);
				events.add(event);
			}
			
		} catch (SQLException e) {
			System.err.println("Error fetching events: " + e.getMessage());
            e.printStackTrace();
		}
		return events;
	}
	
	// Deletes an event by its ID
	public boolean deleteEvent(String eventId) {
        if (eventId == null) return false;

        String sql = "DELETE FROM events WHERE event_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, eventId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting event: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
