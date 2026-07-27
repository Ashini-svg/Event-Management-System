package com.eventmgmt.structures.linear;

public class ActivityHistory {

    private Stack historyStack;

    public ActivityHistory() {
        this.historyStack = new Stack();
    }

    public void logActivity(String actionDescription) {
        historyStack.push(actionDescription);
        System.out.println("Logged Activity: " + actionDescription);
    }

    public String undoLastAction() {
        if (historyStack.isEmpty()) {
            System.out.println("No activities available to undo.");
            return null;
        }
        String undoneAction = historyStack.pop();
        System.out.println("Reverting Action: " + undoneAction);
        return undoneAction;
    }

    public void viewMostRecentActivity() {
        if (historyStack.isEmpty()) {
            System.out.println("No activity logs available.");
        } else {
            System.out.println("Most Recent Activity: " + historyStack.peek());
        }
    }
}