package com.eventmgmt.database;

import com.eventmgmt.model.Attendee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendeeDAO {
	// Inserts or updates an attendee record in the database
	public boolean saveAttendee(Attendee attendee) {
		if (attendee == null) return false;
		
		String sql = "INSERT INTO attendees (attendee_id, attendee_name, email) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE attendee_name = ?, email = ?";
		
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// INSERT values
			pstmt.setString(1, attendee.getAttendeeId());
			pstmt.setString(2, attendee.getName());
			pstmt.setString(3, attendee.getEmail());
			
			// UPDATE fallback values
			pstmt.setString(4, attendee.getName());
            pstmt.setString(5, attendee.getEmail());
			
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.err.println("Error saving attendee: " + e.getMessage());
            e.printStackTrace();
            return false;
		}
	}
	
	// Retrieves all attendee records from the database
	public List<Attendee> getAllAttendees() {
		List<Attendee> attendees = new ArrayList<>();
		String sql = "SELECT * FROM attendees";
		
		try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				String id = rs.getString("attendee_id");
				String name = rs.getString("attendee_name");
				String email = rs.getString("email");
				
				attendees.add(new Attendee(id, name, email));
			}
		} catch (SQLException e) {
			System.err.println("Error fetching attendees: " + e.getMessage());
            e.printStackTrace();
		}
		return attendees;
	}
	
	// Deletes an attendee record by ID
	public boolean deleteAttendee(String attendeeId) {
        if (attendeeId == null) return false;

        String sql = "DELETE FROM attendees WHERE attendee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, attendeeId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting attendee: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
