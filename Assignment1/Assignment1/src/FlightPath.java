import java.io.FileNotFoundException;
import java.util.List;

public class FlightPath {
    private String filePath = "input.txt";
    private Graph g;

    public FlightPath() {
        try {
            this.g = new Graph(filePath);
        } catch (FileNotFoundException e) {
            // Handle the exception here
            e.printStackTrace();
        }
        System.out.println(
                "You are attempting to travel from Macau to the USA.");

        List<String> path = g.findShortestPath("Macau", "USA");
        if (path != null) {
            System.out.println("Shortest path from Macau to USA: " + String.join(" -> ", path));
        } else {
            System.out.println("No path from Macau to USA");
        }
    }
}
