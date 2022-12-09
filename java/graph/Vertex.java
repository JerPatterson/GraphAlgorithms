package graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Vertex {
    Vertex(Vertex vertex) {
        identifier = vertex.identifier;
        adjacents = vertex.adjacents;
        costs = vertex.costs;
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


    public void addAdjacent(Vertex vertex) {
        if (costs.get(vertex) == null) {
            adjacents.add(new NeighborVertex(vertex, 0));
            costs.put(vertex.getIdentifier(), 0);
        }
    }

    public void addAdjacent(Vertex vertex, Integer weight) {
        if (costs.get(vertex) == null) {
            adjacents.add(new NeighborVertex(vertex, weight));
            costs.put(vertex.getIdentifier(), weight);
        }
    }


    public String toString() {
        return identifier;
    }
}
