import java.util.List;

public class SearchResult {
    List<String> path;
    double cost;

    SearchResult(List<String> path, double cost) {
        this.path = path;
        this.cost = cost;
    }
}
