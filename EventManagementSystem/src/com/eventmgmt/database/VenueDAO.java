package com.eventmgmt.database;

import com.eventmgmt.model.Venue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenueDAO {
	// Inserts or updates a new venue record in the database
	public boolean saveVenue(Venue venue) {
		if (venue == null) return false;
		
		String sql = "INSERT INTO venues (venue_id, venue_name, location, capacity, available) " +
				"VALUES (?, ?, ?, ?, ?) " +
				"ON DUPLICATE KEY UPDATE venue_name = ?, location = ?, capacity = ?, available = ?";
		
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Set parameters for INSERT statement
			pstmt.setString(1, venue.getVenueId());
			pstmt.setString(2, venue.getVenueName());
			pstmt.setString(3, venue.getLocation());
			pstmt.setInt(4, venue.getCapacity());
			pstmt.setBoolean(5,  venue.isAvailable());
			
			// Set parameters for UPDATE fallback
            pstmt.setString(6, venue.getVenueName());
            pstmt.setString(7, venue.getLocation());
            pstmt.setInt(8, venue.getCapacity());
            pstmt.setBoolean(9, venue.isAvailable());
			
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.err.println("Error saving venue: " + e.getMessage());
            e.printStackTrace();
            return false;
		}
	}
	
	// Retrieves all venue records from the database
	public List<Venue> getAllVenues() {
		List<Venue> venues = new ArrayList<>();
		String sql = "SELECT * FROM venues";
		
		try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				String id = rs.getString("venue_id");
				String name = rs.getString("venue_name");
				String location = rs.getString("location");
				int capacity = rs.getInt("capacity");
				boolean available = rs.getBoolean("available");
				
				// Instantiates using the 5-parameter constructor in Venue.java
				venues.add(new Venue(id, name, location, capacity, available));
			}
		} catch (SQLException e) {
			System.err.println("Error fetching venues: " + e.getMessage());
            e.printStackTrace();
		}
		return venues;
	}
	
	// Deletes a venue from the database by its ID
	public boolean deleteVenue(String venueId) {
        if (venueId == null) return false;

        String sql = "DELETE FROM venues WHERE venue_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, venueId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting venue: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
