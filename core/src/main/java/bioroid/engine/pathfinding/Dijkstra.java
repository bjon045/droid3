package bioroid.engine.pathfinding;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import bioroid.model.location.Location;

public class Dijkstra {

    public static void calculateShortestPathFromSource(Graph graph, Node source) {
	calculateShortestPathFromSourceToTarget(graph, source, null);
    }

    public static Node calculateShortestPathFromSourceToTarget(Graph graph, Node source, Location target) {
	source.setDistance(0);

	Set<Node> settledNodes = new HashSet<Node>();
	Set<Node> unsettledNodes = new HashSet<Node>();

	unsettledNodes.add(source);

	while (unsettledNodes.size() != 0) {
	    Node currentNode = getLowestDistanceNode(unsettledNodes);
	    unsettledNodes.remove(currentNode);
	    for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
		Node adjacentNode = adjacencyPair.getKey();
		Integer edgeWeight = adjacencyPair.getValue();
		if (!settledNodes.contains(adjacentNode)) {
		    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
		    unsettledNodes.add(adjacentNode);
		}
	    }
	    settledNodes.add(currentNode);
	    if (target != null && currentNode.getLocation().equals(target)) {
		return currentNode;
	    }
	}
	return null;
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
	Node lowestDistanceNode = null;
	int lowestDistance = Integer.MAX_VALUE;
	for (Node node : unsettledNodes) {
	    int nodeDistance = node.getDistance();
	    if (nodeDistance < lowestDistance) {
		lowestDistance = nodeDistance;
		lowestDistanceNode = node;
	    }
	}
	return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
	Integer sourceDistance = sourceNode.getDistance();
	if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
	    evaluationNode.setDistance(sourceDistance + edgeWeigh);
	    LinkedList<Node> shortestPath = new LinkedList<Node>(sourceNode.getShortestPath());
	    shortestPath.add(sourceNode);
	    evaluationNode.setShortestPath(shortestPath);
	}
    }
}
