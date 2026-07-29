package com.eventmgmt.structures.graphs;

import java.util.ArrayList;
import java.util.List;
import com.eventmgmt.model.Venue;

public class Graph {
	
	
	// Store all venues (Graph nodes)
	private List<GraphNode> nodes;
	
	
	// Constructor (Creates an empty graph)
	public Graph() {
		nodes = new ArrayList<>();
	}
	
	
	// Getter
	public List<GraphNode> getNodes(){
		return nodes;
	}
	
	
	// Adds a venue as a graph node
	public void addVenue(Venue venue) {
		if (venue != null && findNode(venue.getVenueId()) == null) {
			nodes.add(new GraphNode(venue));
		}
	}
	
	
	// Finds a graph node using the venue ID
	public GraphNode findNode(String venueId) {
		if (venueId == null) return null;
		
		for(GraphNode node : nodes) {
			if(node.getVenue().getVenueId().equalsIgnoreCase(venueId)) {
				return node;
			}
		}
		return null;
	}
	
	
	// Connect two venues
	public void addConnection(String venueId1, String venueId2) {
		
		GraphNode node1 = findNode(venueId1);
		GraphNode node2 = findNode(venueId2);
		
		if (node1 != null && node2 != null && node1 != node2) {
			node1.addAdjacentNode(node2);
            node2.addAdjacentNode(node1);
		}
	}
	
	
	// Display the graph as an adjacency list
	public void displayGraph() {
		
		for(GraphNode node : nodes) {
			
			System.out.print(node.getVenue().getVenueName() + " -> ");
			
			for(GraphNode neighbour : node.getAdjacentNodes()) {
				System.out.print(neighbour.getVenue().getVenueName() + " ");
			}
			
			System.out.println();
		}
	}
	

}
