CREATE DATABASE IF NOT EXISTS eventmgmt_db;
USE eventmgmt_db;

CREATE TABLE IF NOT EXISTS venues (
	venue_id VARCHAR(50) PRIMARY KEY,
    venue_name VARCHAR(100) NOT NULL,
    location VARCHAR(150),
    capacity INT NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS events (
	event_id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    venue_id VARCHAR(50),
    event_datetime DATETIME NOT NULL,
    capacity INT NOT NULL,
    attendees_count INT DEFAULT 0,
    FOREIGN KEY (venue_id) REFERENCES venues(venue_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS attendees (
	attendee_id VARCHAR(50) PRIMARY KEY,
    attendee_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings (
	booking_id VARCHAR(50) PRIMARY KEY,
    event_id VARCHAR(50) NOT NULL,
    attendee_id VARCHAR(50) NOT NULL,
    booking_datetime DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE,
    FOREIGN KEY (attendee_id) REFERENCES attendees(attendee_id) ON DELETE CASCADE
);

INSERT INTO venues (venue_id, venue_name, location, capacity, available) 
VALUES
	('V001', 'Grand Ballroom', 'Building A, Floor 2', 500, TRUE),
	('V002', 'Tech Auditorium', 'Innovation Center', 250, TRUE),
	('V003', 'Executive Boardroom', 'Corporate Tower', 30, TRUE),
	('V004', 'Open Air Plaza', 'North Campus', 1000, TRUE),
	('V005', 'Virtual Studio 1', 'Online / Main Hub', 5000, TRUE);
    
INSERT INTO events (event_id, title, category, venue_id, event_datetime, capacity, attendees_count) 
VALUES
	('E101', 'Tech Summit 2026', 'Corporate & Business', 'V002', '2026-09-15 09:00:00', 250, 2),
	('E102', 'Annual Gala Dinner', 'Social & Personal', 'V001', '2026-10-20 18:30:00', 500, 1),
	('E103', 'Cultural Music Fest', 'Entertainment & Cultural', 'V004', '2026-11-05 16:00:00', 1000, 0),
	('E104', 'Java Workshop', 'Educational & Skill-Building', 'V003', '2026-08-25 10:00:00', 30, 1),
	('E105', 'Global AI Webinar', 'Virtual or Hybrid', 'V005', '2026-12-01 14:00:00', 5000, 1);

INSERT INTO attendees (attendee_id, attendee_name, email) 
VALUES
	('A001', 'Alice Johnson', 'alice.johnson@example.com'),
	('A002', 'Bob Smith', 'bob.smith@example.com'),
	('A003', 'Charlie Brown', 'charlie.brown@example.com'),
	('A004', 'Diana Prince', 'diana.prince@example.com'),
	('A005', 'Evan Wright', 'evan.wright@example.com');
    
INSERT INTO bookings (booking_id, event_id, attendee_id, booking_datetime) 
VALUES
	('B-1001', 'E101', 'A001', '2026-08-11 09:00:00'),
	('B-1002', 'E101', 'A002', '2026-08-11 09:15:00'),
	('B-1003', 'E102', 'A003', '2026-08-11 10:00:00'),
	('B-1004', 'E104', 'A004', '2026-08-11 11:30:00'),
	('B-1005', 'E105', 'A005', '2026-08-11 12:00:00');
