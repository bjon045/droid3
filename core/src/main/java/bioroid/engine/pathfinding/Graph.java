package bioroid.engine.pathfinding;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<Node>();

    public Set<Node> getNodes() {
	return nodes;
    }

    public void setNodes(Set<Node> nodes) {
	this.nodes = nodes;
    }

    public void addNode(Node nodeA) {
	nodes.add(nodeA);
    }

    public void resetGraph() {
	for (Node node : nodes) {
	    node.resetNode();
	}
    }

}
