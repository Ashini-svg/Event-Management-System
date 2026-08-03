package com.eventmgmt.structures.graphs;

import java.util.ArrayList;
import java.util.List;

import com.eventmgmt.structures.linear.Stack;

public class DFS {
	// Performs DFS starting from a given venue
	public void traverse(Graph graph, String startVenueId) {

	    GraphNode startNode = graph.findNode(startVenueId);

	    if (startNode == null) {
	        System.out.println("Starting venue not found.");
	        return;
	    }

	    Stack<GraphNode> stack = new Stack<>();
	    List<GraphNode> visited = new ArrayList<>();

	    stack.push(startNode);

	    System.out.println("DFS Traversal:");

	    while (!stack.isEmpty()) {

	        GraphNode current = stack.pop();

	        if (!visited.contains(current)) {

	            visited.add(current);

	            System.out.println(current.getVenue().getVenueName());

	            // Push neighbours onto the stack
	            List<GraphNode> neighbours = current.getAdjacentNodes();

	            for (int i = neighbours.size() - 1; i >= 0; i--) {

	                GraphNode neighbour = neighbours.get(i);

	                if (!visited.contains(neighbour)) {
	                    stack.push(neighbour);
	                }
	            }
	        }
	    }
	}

}
