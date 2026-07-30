package com.eventmgmt.structures.linear;

public class CategoryArray {
	
	// Fixed event Categories
	private String[] categories = {
	
			"Corporate & Business",
			"Social & Personal",
			"Entertainment & Cultural",
			"Educational & Skill-Building",
			"Virtual or Hybrid"
	};
	
	// Display all categories
	public void displayCategories() {
		
		System.out.println("===== Event Categories =====");
		
		 for (int i = 0; i < categories.length; i++) {
	            System.out.println((i + 1) + ". " + categories[i]);
	        }
	    }

	    // Get category by index
	    public String getCategory(int index) {

	        if (index >= 0 && index < categories.length) {
	            return categories[index];
	        }

	        return null;
	    }

	    // Get all categories
	    public String[] getCategories() {
	        return categories;
	    }

	    // Total number of categories
	    public int getCategoryCount() {
	        return categories.length;
	    }
	
		
		
	}


