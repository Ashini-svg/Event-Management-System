package com.eventmgmt.structures.linear;

public class ActivityHistory {

    private Stack<String> historyStack;

    // Constructor
    public ActivityHistory() {
        historyStack = new Stack<>();
    }

    // Log Activity
    public void logActivity(String activity) {

        historyStack.push(activity);

        System.out.println("Activity Logged: " + activity);
    }

    // Undo Last Activity
    public String undoLastActivity() {

        if (historyStack.isEmpty()) {
            System.out.println("No activities to undo.");
            return null;
        }

        String activity = historyStack.pop();

        System.out.println("Undo Activity: " + activity);

        return activity;
    }

    // View Latest Activity
    public void viewLatestActivity() {

        if (historyStack.isEmpty()) {
            System.out.println("No recent activities.");
            return;
        }

        System.out.println("Latest Activity: " + historyStack.peek());
    }

    // Display All Activities
    public void displayActivities() {

        historyStack.display();
    }
}