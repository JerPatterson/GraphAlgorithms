package graph;
import java.util.*;

public class NoWeightGraph {
    NoWeightGraph() {
        vertex = new HashMap<>();
        edges = new PriorityQueue<>();
    }


    private final HashMap<String, Vertex> vertex;
    private final PriorityQueue<Edge> edges;


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


    public ArrayList<Vertex> dfs(String startIdentifier) {
        Stack<Vertex> stack = new Stack<>();
        HashSet<String> visited = new HashSet<>();
        ArrayList<Vertex> path = new ArrayList<>();

        stack.push(vertex.get(startIdentifier));
        visited.add(startIdentifier);
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            for (Vertex neighbor : current.getAdjacent()) {
                if (!visited.contains(neighbor.getIdentifier())) {
                    stack.push(neighbor);
                }
                visited.add(neighbor.getIdentifier());
            }
            path.add(current);
        }

        return path;
    }


    public void print() {
        System.out.println("Graph Actual Nodes : " + vertex.keySet());
        for (Edge edge : edges) {
            edge.print();
        }
    }
}
