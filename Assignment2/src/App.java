import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        File map = new File("src/map.txt");
        File heuristic = new File("src/heuristic.txt");

        aStarSearch aStar = new aStarSearch();

        aStar.loadGraph(map);
        aStar.initializeHeuristic(heuristic);

    }
}
