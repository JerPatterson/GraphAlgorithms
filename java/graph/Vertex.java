package graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vertex {
    Vertex(String name) {
        identifier = name;
        adjacents = new ArrayList<>();
        costs = new HashMap<>();
    }


    private String identifier;
    private final ArrayList<Vertex> adjacents;
    private final Map<Vertex, Integer> costs;


    public String getIdentifier() { return identifier; }
    public ArrayList<Vertex> getAdjacent() { return adjacents; }
    public Integer getCostToAdjacent(Vertex vertex) { return costs.get(vertex); }


    public void addAdjacent(Vertex vertex) {
        if (costs.get(vertex) == null) {
            adjacents.add(vertex);
            costs.put(vertex, null);
        }
    }

    public void addAdjacent(Vertex vertex, Integer weight) {
        if (costs.get(vertex) == null) {
            adjacents.add(vertex);
            costs.put(vertex, weight);
        } else if (costs.get(vertex).compareTo(weight) > 0) {
            costs.put(vertex, weight);
        }
    }

    public String toString() {
        return identifier;
    }
}
