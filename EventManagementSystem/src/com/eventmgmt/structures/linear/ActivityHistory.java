package com.eventmgmt.structures.linear;

public class ActivityHistory {
	
	// Pairs a description with the code needed to reverse it
	public static class UndoableAction {
		private final String description;
		private final Runnable undo;
		
		public UndoableAction(String description, Runnable undo) {
			this.description = description;
			this.undo = undo;
		}
		
		public String getDescription() {
			return description;
		}
		
		public void runUndo() {
			if (undo != null) {
				undo.run();
			}
		}
		
		@Override
		public String toString() {
			return description;
		}
		
	}

    private Stack<UndoableAction> historyStack;

    // Constructor
    public ActivityHistory() {
        historyStack = new Stack<>();
    }

    // Log an activity with no reversal available
    public void logActivity(String activity) {
    	logActivity(activity, null);
    }
    
    
    // Log an activity together with the code needed to reverse it
    public void logActivity(String activity, Runnable undo) {
    	historyStack.push(new UndoableAction(activity, undo));
    	
    	System.out.println("Activity logged: " + activity);
    }

    // Undo Last Activity
    public String undoLastActivity() {

        if (historyStack.isEmpty()) {
            System.out.println("No activities to undo.");
            return null;
        }

        UndoableAction action = historyStack.pop();
        action.runUndo();

        System.out.println("Undo Activity: " + action.getDescription());

        return action.getDescription();
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