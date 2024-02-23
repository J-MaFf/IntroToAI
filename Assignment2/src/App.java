import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        File map = new File("src/map.txt");
        File heuristic = new File("src/heuristic.txt");

        aStarSearch instance = new aStarSearch();

        instance.loadGraph(map);
        instance.initializeHeuristic(heuristic);

    }
}
