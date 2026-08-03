package com.eventmgmt.structures.graphs;

import java.util.ArrayList;
import java.util.List;

import com.eventmgmt.structures.linear.Queue;

public class BFS {
	
	// Performs BFS starting from a given venue
	public void traverse(Graph graph, String startVenueId) {

	    GraphNode startNode = graph.findNode(startVenueId);

	    if (startNode == null) {
	        System.out.println("Starting venue not found.");
	        return;
	    }

	    
	    Queue<GraphNode> queue = new Queue<>();
	    List<GraphNode> visited = new ArrayList<>();

	    
	    queue.enqueue(startNode);
	    visited.add(startNode);

	    
	    System.out.println("BFS Traversal:");

	    
	    while (!queue.isEmpty()) {

	        GraphNode current = queue.dequeue();

	        System.out.println(current.getVenue().getVenueName());

	        for (GraphNode neighbour : current.getAdjacentNodes()) {

	            if (!visited.contains(neighbour)) {

	                visited.add(neighbour);
	                queue.enqueue(neighbour);

	            }
	        }
	    }
	}
	

}
