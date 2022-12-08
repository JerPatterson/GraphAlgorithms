package graph;
import java.util.*;

public class NoWeightGraph {
    NoWeightGraph() {
        vertex = new HashMap<>();
        edges = new PriorityQueue<>();
    }


    protected final HashMap<String, Vertex> vertex;
    protected final PriorityQueue<Edge> edges;


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
                    visited.add(neighbor.getIdentifier());
                }
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

    private LinkedList<Vertex> getDfsClosingTimes(String startIdentifier) {
        int stackSize;
        LinkedList<Vertex> closingTimes = new LinkedList<>();

        Stack<Vertex> stack = new Stack<>();
        Stack<Vertex> unstacked = new Stack<>();
        HashSet<String> visited = new HashSet<>();

        stack.add(vertex.get(startIdentifier));
        visited.add(startIdentifier);
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            System.out.println(current);
            unstacked.push(current);
            stackSize = stack.size();
            for (Vertex neighbor : current.getAdjacent()) {
                if (!visited.contains(neighbor.getIdentifier())) {
                    stack.add(neighbor);
                    visited.add(neighbor.getIdentifier());
                }
            }
            if (stackSize == stack.size()) {
                closingTimes.push(unstacked.pop());
            }
        }
        while(!unstacked.isEmpty()) {
            closingTimes.push(unstacked.pop());
        }

        System.out.println(closingTimes);
        return closingTimes;
    }

    public ArrayList<ArrayList<String>> getStronglyConnectedComponents() {
        NoWeightGraph transposedGraph = getTransposed();
        HashSet<String> visited = new HashSet<>();

        ArrayList<String> currentComponent;
        ArrayList<ArrayList<String>> connectedComponents = new ArrayList<>();
        String identifier = edges.peek().getEdge()[0].getIdentifier();
        for (Vertex vertex1 : getDfsClosingTimes(identifier)) {
            if (!visited.contains(vertex1.getIdentifier())) {
                currentComponent = new ArrayList<>();
                for (Vertex vertex2 : transposedGraph.dfs(vertex1.getIdentifier())) {
                    if (!visited.contains(vertex2.getIdentifier())) {
                        currentComponent.add(vertex2.getIdentifier());
                        visited.add(vertex2.getIdentifier());
                    }
                }
                connectedComponents.add(currentComponent);
            }
        }

        return connectedComponents;
    }


    public void print() {
        System.out.println("Graph Actual Nodes : " + vertex.keySet());
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }
}
