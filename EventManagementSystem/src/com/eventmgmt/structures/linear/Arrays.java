package com.eventmgmt.structures.linear;

public class Arrays {
	    // Fixed array of event categories
	    private String[] categories = {
	        "Workshop",
	        "Seminar",
	        "Conference",
	        "Competition",
	        "Hackathon"
	    };

	    // Display all categories
	    public void displayCategories() {
	        System.out.println("===== Event Categories =====");
	        for (int i = 0; i < categories.length; i++) {
	            System.out.println((i + 1) + ". " + categories[i]);
	        }
	    }

	    // Return category by index
	    public String getCategory(int index) {
	        if (index >= 0 && index < categories.length) {
	            return categories[index];
	        }
	        return null;
	    }

	    // Return the whole array
	    public String[] getCategories() {
	        return categories;
	    }
	}

