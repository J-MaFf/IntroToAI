public class Node implements Comparable<Node> {
    String name;
    double gCost; // Cost from start node to this node
    double fCost; // gCost + heuristic cost to the goal
    Node parent; // To track the path

    public Node(String name) {
        this.name = name;
        this.gCost = Double.MAX_VALUE;
        this.fCost = Double.MAX_VALUE;
        this.parent = null;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.fCost, other.fCost);
    }
}
