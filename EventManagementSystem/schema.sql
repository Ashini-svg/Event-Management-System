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
