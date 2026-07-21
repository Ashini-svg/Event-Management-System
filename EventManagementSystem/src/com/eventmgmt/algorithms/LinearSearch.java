package com.eventmgmt.algorithms;

import com.eventmgmt.model.Attendee;
import com.eventmgmt.model.Event;

public class LinearSearch {

    // Search Event by Event ID
    public static Event searchEventById(Event[] events, String eventId) {

        if (events == null) {
            return null;
        }

        for (Event event : events) {

            if (event != null &&
                event.getEventId().equalsIgnoreCase(eventId)) {

                return event;
            }
        }

        return null;
    }

    // Search Attendee by Attendee ID
    public static Attendee searchAttendeeById(Attendee[] attendees, String attendeeId) {

        if (attendees == null) {
            return null;
        }

        for (Attendee attendee : attendees) {

            if (attendee != null &&
                attendee.getAttendeeId().equalsIgnoreCase(attendeeId)) {

                return attendee;
            }
        }

        return null;
    }

    // Search Event by Title
    public static Event searchEventByTitle(Event[] events, String title) {

        if (events == null) {
            return null;
        }

        for (Event event : events) {

            if (event != null &&
                event.getTitle().equalsIgnoreCase(title)) {

                return event;
            }
        }

        return null;
    }

    // Search Attendee by Name
    public static Attendee searchAttendeeByName(Attendee[] attendees, String name) {

        if (attendees == null) {
            return null;
        }

        for (Attendee attendee : attendees) {

            if (attendee != null &&
                attendee.getName().equalsIgnoreCase(name)) {

                return attendee;
            }
        }

        return null;
    }
}