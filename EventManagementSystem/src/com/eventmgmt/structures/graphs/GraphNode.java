package com.eventmgmt.structures.graphs;

import java.util.ArrayList;
import java.util.List;

import com.eventmgmt.model.Venue;
	
public class GraphNode {
	
	// Stores the venue represented by this graph node
	private Venue venue;
	
	// Stores all connected neighboring venues
	private List<GraphNode> adjacentNodes;
	
	
	// Constructor (Creates a graph node for a venue)
	
	public GraphNode(Venue venue) {
		this.venue = venue;
		this.adjacentNodes = new ArrayList<>();
	}
	
	
	//Getter methods
	
	public Venue getVenue() {
		return venue;
	} 
	
	public List<GraphNode> getAdjacentNodes(){
		return adjacentNodes;
	}	
	
	
	// Method to connect another node (Add a neighboring venue to this node)
	
	public void addAdjacentNode(GraphNode node) {
		if(!adjacentNodes.contains(node)) {
			adjacentNodes.add(node);
		}
	}
	
	
	@Override
	public String toString() {
		return venue.getVenueName();
	}
	

}
