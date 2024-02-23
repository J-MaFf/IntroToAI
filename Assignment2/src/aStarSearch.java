import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class aStarSearch {
    private Map<String, Map<String, Double>> graph = new HashMap<>(); // graph = node -> (neighbor, cost)
    private Map<String, Double> heuristic = new HashMap<>(); // heuristic = node -> heruistic value
    private PriorityQueue<Node> openSet = new PriorityQueue<>();
    private Map<String, Node> allNodes = new HashMap<>();

    /**
     * Loads a graph from a file.
     * 
     * Intuition:
     * 
     * Split line into 3 parts (node, node, weight)
     * 
     * If node1 is not in the map yet, initialize new map to store (nebhior,
     * edgeWeight)
     * 
     * Store node2, edgeWeight in secondary map.
     * 
     * We now have (node1 -> (node2 -> edgeWeight))
     *
     * @param file the file containing the graph data
     * @throws FileNotFoundException if the file is not found
     */
    public void loadGraph(File file) throws FileNotFoundException { // types not set
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(",");
            String node1 = parts[0];
            String node2 = parts[1];
            Double weight = Double.parseDouble(parts[2]);

            graph.putIfAbsent(node1, new HashMap<>());
            graph.get(node1).put(node2, weight);
            graph.putIfAbsent(node2, new HashMap<>());
            graph.get(node2).put(node1, weight);

        }
        sc.close();
    }

    /**
     * Initializes the heuristic values for each node based on the given file.
     * Populates huristic map with node -> heuristic value
     * 
     * @param file the file containing the node heuristic values
     * @throws FileNotFoundException if the file is not found
     */
    public void initializeHeuristic(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(",");
            String node = parts[0];
            Double heuristicVal = Double.parseDouble(parts[1]);
            heuristic.put(node, heuristicVal);
        }
        sc.close();
    }

    /**
     * Finds the path from the start node to the goal node using the A* search
     * algorithm.
     *
     * @param start The name of the start node.
     * @param goal  The name of the goal node.
     * @return A list of strings representing the path from the start node to the
     *         goal node.
     *         If no path is found, an empty list is returned.
     */
    public SearchResult findPath(String start, String goal) {
        for (String nodeName : graph.keySet()) {
            Node node = new Node(nodeName);
            allNodes.put(nodeName, node);
        }

        Node startNode = allNodes.get(start);
        startNode.gCost = 0;
        startNode.fCost = heuristic.getOrDefault(start, 0.0);

        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.name.equals(goal)) {
                return new SearchResult(reconstructPath(current), current.gCost);
            }

            for (Map.Entry<String, Double> neighborEntry : graph.getOrDefault(current.name, Collections.emptyMap())
                    .entrySet()) {
                Node neighbor = allNodes.get(neighborEntry.getKey());
                double tentativeGCost = current.gCost + neighborEntry.getValue();

                if (tentativeGCost < neighbor.gCost) {
                    neighbor.parent = current;
                    neighbor.gCost = tentativeGCost;
                    neighbor.fCost = tentativeGCost + heuristic.getOrDefault(neighbor.name, 0.0);
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return new SearchResult(Collections.emptyList(), Double.MAX_VALUE); // Path not found
    }

    /**
     * Reconstructs the path from the start node to the current node.
     * 
     * @param current The current node.
     * @return The list of node names representing the path.
     */
    private List<String> reconstructPath(Node current) {
        List<String> path = new ArrayList<>();
        Node temp = current;

        while (temp != null) {
            path.add(temp.name);
            temp = temp.parent;
        }

        Collections.reverse(path);
        return path;
    }
}
