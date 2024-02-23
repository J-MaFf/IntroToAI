import java.io.File;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        File map = new File("src/map.txt");
        File heuristic = new File("src/heuristic.txt");

        aStarSearch aStar = new aStarSearch();

        aStar.loadGraph(map);
        aStar.initializeHeuristic(heuristic);

        SearchResult result = aStar.findPath("GHS", "William Pond Halloween Party");
        if (!result.path.isEmpty()) {
            System.out.println("Path: " + String.join(" -> ", result.path));
            System.out.printf("Cost: %.2f\n", result.cost);
        } else {
            System.out.println("Path not found");
        }
    }
}
