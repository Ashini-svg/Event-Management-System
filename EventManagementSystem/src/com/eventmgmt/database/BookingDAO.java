package com.eventmgmt.database;

import com.eventmgmt.model.Booking;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
	// Add a new ticket reservation
	public boolean addBooking(Booking booking) {
		if (booking == null) return false;
		
		String sql = "INSERT INTO bookings (booking_id, event_id, attendee_id, booking_datetime) " +
		"VALUES (?, ?, ?, ?)";
		
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, booking.getBookingId());
			pstmt.setString(2, booking.getEventId());
			pstmt.setString(3, booking.getAttendeeId());
			pstmt.setTimestamp(4, Timestamp.valueOf(booking.getBookingDateTime()));
			
			return pstmt.executeUpdate() > 0;
			
 		} catch (SQLException e) {
 			System.err.println("Error adding booking: " + e.getMessage());
 			e.printStackTrace();
 			return false;
 		}
	}
	
	// Retrieves all registered bookings
	public List<Booking> getAllBookings() {
		List<Booking> bookings = new ArrayList<>();
		String sql = "SELECT * FROM bookings";
		
		try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				String bookingId = rs.getString("booking_id");
				String eventId = rs.getString("event_id");
				String attendeeId = rs.getString("attendee_id");
				LocalDateTime bookingDateTime = rs.getTimestamp("booking_datetime").toLocalDateTime();
				
				Booking booking = new Booking(bookingId, eventId, attendeeId, bookingDateTime);
				bookings.add(booking);
			}
		} catch (SQLException e) {
			System.err.println("Error fetching booking: " + e.getMessage());
			e.printStackTrace();
		}
		return bookings;
	}
	
	// Deletes a booking by its ID
	public boolean deleteBooking(String bookingId) {
		if (bookingId == null) return false;
		
		String sql = "DELETE FROM bookings WHERE booking_id = ?";
		
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, bookingId);
			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.err.println("Error deleting booking: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
