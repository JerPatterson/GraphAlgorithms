package graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vertex {
    Vertex(String name) {
        identifier = name;
        adjacents = new ArrayList<>();
        costs = new HashMap<Vertex, Integer>();
    }


    private String identifier;
    private final ArrayList<Vertex> adjacents;
    private final Map<Vertex, Integer> costs;


    public String getIdentifier() { return identifier; }
    public ArrayList<Vertex> getAdjacent() { return adjacents; }
    public Integer getCostToAdjacent(Vertex adjVertex) { return costs.get(adjVertex); }


    public void addAdjacent(Vertex adjVertex) {
        if (costs.get(adjVertex) == null) {
            adjacents.add(adjVertex);
            costs.put(adjVertex, 0);
        }
    }

    public void addAdjacent(Vertex adjVertex, Integer weight) {
        if (costs.get(adjVertex) == null) {
            adjacents.add(adjVertex);
            costs.put(adjVertex, weight);
        }
        else if (costs.get(adjVertex).compareTo(weight) > 0) {
            costs.put(adjVertex, weight);
        }
    }
}
