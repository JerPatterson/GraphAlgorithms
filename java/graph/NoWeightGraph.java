package graph;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class NoWeightGraph {
    NoWeightGraph() {
        vertex = new HashMap<String, Vertex>();
        edges = new PriorityQueue<Edge>();
    }


    private HashMap<String, Vertex> vertex;
    private PriorityQueue<Edge> edges;


    public void addNewVertex(Integer identifier, Integer[] adjacents) {
        int i = 0;
        String[] newAdjacents = new String[adjacents.length];
        for (Integer adjIdentifier : adjacents) {
            newAdjacents[i++] = adjIdentifier.toString();
        }
        addNewVertex(identifier.toString(), newAdjacents);
    }

    public void addNewVertex(String identifier, String[] adjacents) {
        Vertex current;
        current = vertex.get(identifier);
        if (current == null) {
            current = new Vertex(identifier);
            vertex.put(identifier, current);
        }

        Vertex adjacent;
        for (String adjIdentifier : adjacents) {
            adjacent = vertex.get(adjIdentifier);
            if (adjacent == null) {
                adjacent = new Vertex(adjIdentifier);
                vertex.put(adjIdentifier, adjacent);
            }

            current.addAdjacent(adjacent);
            edges.add(new Edge(current, adjacent));
        }
    }


    public void print() {
        System.out.println("Graph Actual Nodes : " + vertex.keySet());
        for (Edge edge : edges) {
            edge.print();
        }
    }
}
