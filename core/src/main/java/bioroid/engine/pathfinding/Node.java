package bioroid.engine.pathfinding;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bioroid.model.location.Location;

public class Node {

    private Location location;

    private List<Node> shortestPath = new LinkedList<Node>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Node, Integer> adjacentNodes = new HashMap<Node, Integer>();

    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
	this.location = location;
    }

    public List<Node> getShortestPath() {
	return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
	this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
	return distance;
    }

    public void setDistance(Integer distance) {
	this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
	return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
	this.adjacentNodes = adjacentNodes;
    }

    public void addDestination(Node destination, int distance) {
	adjacentNodes.put(destination, distance);
    }

    public void resetNode() {
	distance = Integer.MAX_VALUE;
	shortestPath.clear();
    }
}