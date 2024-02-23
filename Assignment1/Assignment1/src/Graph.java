import java.util.*;
import java.io.*;

/**
 * This class represents a directed graph using adjacency list representation.
 */
public class Graph {
    private int numVertices; // Number of vertices in the graph
    private LinkedList<Integer> adjLists[]; // Array of adjacency lists for each vertex
    private Map<String, Integer> cityToIndexMap; // Map from city names to vertex indices
    private Map<Integer, String> indexToCityMap; // Map from vertex indices to city names
    private String filePath; // Path to the file from which the graph data is read

    /**
     * Constructor that takes a file path, reads the graph data from the file, and
     * builds the graph.
     * 
     * @param filePath Path to the file from which the graph data is read
     * @throws FileNotFoundException if the file does not exist
     */
    Graph(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.cityToIndexMap = new HashMap<>();
        this.indexToCityMap = new HashMap<>();
        buildGraph();
    }

    /**
     * Reads the graph data from the file and builds the graph.
     * 
     * @throws FileNotFoundException if the file does not exist
     */
    private void buildGraph() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        if (scanner.hasNextLine()) {
            numVertices = Integer.parseInt(scanner.nextLine().trim());
            adjLists = new LinkedList[numVertices];
            for (int i = 0; i < numVertices; i++) {
                adjLists[i] = new LinkedList<>();
            }
        }

        int index = 0;
        while (scanner.hasNextLine()) {
            String[] cities = scanner.nextLine().split("\\s+");
            if (!cityToIndexMap.containsKey(cities[0])) {
                cityToIndexMap.put(cities[0], index);
                indexToCityMap.put(index, cities[0]);
                index++;
            }
            if (!cityToIndexMap.containsKey(cities[1])) {
                cityToIndexMap.put(cities[1], index);
                indexToCityMap.put(index, cities[1]);
                index++;
            }
            addEdge(cityToIndexMap.get(cities[0]), cityToIndexMap.get(cities[1]));
        }

        scanner.close();
    }

    /**
     * Adds an edge to the graph.
     * 
     * @param src  Index of the source vertex
     * @param dest Index of the destination vertex
     */
    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    /**
     * Performs a Breadth-First Search (BFS) traversal of the graph from a given
     * source vertex.
     * 
     * @param s Index of the source vertex
     */
    void BFS(int s) {
        boolean visited[] = new boolean[numVertices];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(indexToCityMap.get(s) + "(" + s + ") ");

            Iterator<Integer> i = adjLists[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    /**
     * Performs a Depth-First Search (DFS) traversal of the graph from a given
     * vertex.
     * 
     * @param v Index of the vertex
     */
    void DFS(int v) {
        boolean visited[] = new boolean[numVertices];
        DFSUtil(v, visited);
    }

    /**
     * Utility method for DFS traversal.
     * 
     * @param v       Index of the current vertex
     * @param visited Array that keeps track of which vertices have been visited
     */
    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(indexToCityMap.get(v) + "(" + v + ") ");

        Iterator<Integer> i = adjLists[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    /**
     * Finds the shortest path from one city to another.
     * 
     * @param startCity Name of the start city
     * @param endCity   Name of the end city
     * @return List of city names representing the shortest path from startCity to
     *         endCity, or null if no path exists
     */
    public List<String> findShortestPath(String startCity, String endCity) {
        int start = cityToIndexMap.get(startCity);
        int end = cityToIndexMap.get(endCity);

        int[] pred = new int[numVertices];
        Arrays.fill(pred, -1);

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[numVertices];

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (int u : adjLists[v]) {
                if (!visited[u]) {
                    visited[u] = true;
                    pred[u] = v;
                    queue.add(u);

                    if (u == end) {
                        LinkedList<String> path = new LinkedList<>();
                        for (int i = end; i != -1; i = pred[i]) {
                            path.addFirst(indexToCityMap.get(i));
                        }
                        return path;
                    }
                }
            }
        }

        return null; // No path exists
    }
}