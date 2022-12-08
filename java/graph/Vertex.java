package graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Vertex {
    Vertex() {
        this(null);
    }

    Vertex(String name) {
        identifier = name;
        adjacents = new PriorityQueue<>();
        costs = new HashMap<>();
    }

    private String identifier;
    private final PriorityQueue<NeighborVertex> adjacents;
    private final HashMap<String, Integer> costs;


    public String getIdentifier() { return identifier; }
    public ArrayList<NeighborVertex> getAdjacents() {
        ArrayList<NeighborVertex> adjacentsReturn = new ArrayList<>();
        PriorityQueue<NeighborVertex> adjacentsCopy = new PriorityQueue<>(adjacents);
        for (int i = 0; i < adjacents.size(); ++i) {
            adjacentsReturn.add(adjacentsCopy.poll());
        }
        return adjacentsReturn;
    }
    public Integer getCostToAdjacent(String identifier) { return costs.get(identifier); }
    public Integer getCostToAdjacent(Vertex vertex) { return costs.get(vertex.getIdentifier()); }


    public void addAdjacent(Vertex vertex) {
        if (costs.get(vertex) == null) {
            adjacents.add(new NeighborVertex(vertex.getIdentifier(), null));
            costs.put(vertex.getIdentifier(), null);
        }
    }

    public void addAdjacent(Vertex vertex, Integer weight) {
        if (costs.get(vertex) == null) {
            adjacents.add(new NeighborVertex(vertex.getIdentifier(), weight));
            costs.put(vertex.getIdentifier(), weight);
        }
    }


    public String toString() {
        return identifier;
    }
}
