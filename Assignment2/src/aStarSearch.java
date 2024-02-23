import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class aStarSearch {
    private Map<String, Map<String, Double>> graph = new HashMap<>(); // graph = node -> (neighbor, cost)
    private Map<String, Double> heuristic = new HashMap<>(); // heuristic = node -> heruistic value

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
            Double weight = Double.parseDouble(parts[3]);

            graph.putIfAbsent(node1, new HashMap<String, Double>());
            graph.get(node1).put(node2, weight);

        }
        sc.close();
    }

    public void initializeHeuristic(File file){
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine){
            String parts = sc.nextLine.split(",");
            String node = parts[0];
            Double heuristicVal = parts[1];
            heuristic.put
        }
    }

    public void search(int start, int goal) { // types not set

    }

    public void reconstructPath() { // types not set

    }
}
