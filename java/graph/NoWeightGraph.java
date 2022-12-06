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
        class SpecialStack<AnyType> extends LinkedList<AnyType> {
            @Override
            public boolean add(AnyType anyType) {
                super.addFirst(anyType);
                return super.getFirst() == anyType;
            }
        }
        return search(startIdentifier, new SpecialStack<>());
    }

    public ArrayList<Vertex> bfs(String startIdentifier) {
        return search(startIdentifier, new LinkedList<>());
    }

    private ArrayList<Vertex> search(String startIdentifier, Deque<Vertex> dataStructure) {
        HashSet<String> visited = new HashSet<>();
        ArrayList<Vertex> path = new ArrayList<>();

        dataStructure.add(vertex.get(startIdentifier));
        visited.add(startIdentifier);
        while (!dataStructure.isEmpty()) {
            Vertex current = dataStructure.pop();
            for (Vertex neighbor : current.getAdjacent()) {
                if (!visited.contains(neighbor.getIdentifier())) {
                    dataStructure.add(neighbor);
                }
                visited.add(neighbor.getIdentifier());
            }
            path.add(current);
        }

        return path;
    }


    public NoWeightGraph getTransposed() {
        NoWeightGraph newGraph = new NoWeightGraph();

        for (Edge edge : edges) {
            newGraph.addNewVertex(edge.getEdge()[1].getIdentifier(),
                    new String[] { edge.getEdge()[0].getIdentifier() });
        }

        return newGraph;
    }

    public Integer strongConnectedComponents() {
        int count = 0;
        NoWeightGraph transposedGraph = getTransposed();
        HashSet<String> visited = new HashSet<>();

        for (String identifier : vertex.keySet()) {
            if (!visited.contains(identifier)) {
                for (Vertex neighbor : transposedGraph.dfs(identifier)) {
                    visited.add(neighbor.getIdentifier());
                }
                count++;
            }
        }

        return count;
    }


    public void print() {
        System.out.println("Graph Actual Nodes : " + vertex.keySet());
        for (Edge edge : edges) {
            edge.print();
        }
    }
}
