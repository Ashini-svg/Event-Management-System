package com.eventmgmt.structures.graphs;

import com.eventmgmt.model.Venue;

public class VenueGraphManager {

    // Graph that stores all venue connections
    private Graph graph;

    // Graph traversal algorithms
    private BFS bfs;
    private DFS dfs;

    // constructor
    public VenueGraphManager() {

        graph = new Graph();
        bfs = new BFS();
        dfs = new DFS();

    }

    // Adds a venue to the graph
    public void addVenue(Venue venue) {
        graph.addVenue(venue);
    }

    // Connects two venues
    public void connectVenues(String venueId1, String venueId2) {
        graph.addConnection(venueId1, venueId2);
    }

    // Displays all venue connections
    public void displayGraph() {
        graph.displayGraph();
    }

    // Performs BFS
    public void performBFS(String startVenueId) {
        bfs.traverse(graph, startVenueId);
    }

    // Performs DFS
    public void performDFS(String startVenueId) {
        dfs.traverse(graph, startVenueId);
    }

    // Getter
    public Graph getGraph() {
        return graph;
    }
}
