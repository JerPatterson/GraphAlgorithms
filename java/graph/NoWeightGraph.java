package graph;
import java.util.*;

public class NoWeightGraph {
    NoWeightGraph() {
        vertices = new ArrayList<>();
        verticesMap = new HashMap<>();
        edges = new PriorityQueue<>();
    }

    private final ArrayList<Vertex> vertices;
    protected final HashMap<String, Vertex> verticesMap;
    protected final PriorityQueue<Edge> edges;


    public void addNewVertex(Integer identifier, Integer[] adjacents) {
        int i = 0;
        String[] stringAdjacents = new String[adjacents.length];
        for (Integer adjIdentifier : adjacents) {
            stringAdjacents[i++] = adjIdentifier.toString();
        }
        addNewVertex(identifier.toString(), stringAdjacents);
    }

    public void addNewVertex(String identifier, String[] adjacents) {
        Vertex current = verticesMap.get(identifier);
        if (current == null) {
            current = new Vertex(identifier);
            vertices.add(current);
            verticesMap.put(identifier, current);
        }
        Vertex adjacent;
        for (String adjIdentifier : adjacents) {
            adjacent = verticesMap.get(adjIdentifier);
            if (adjacent == null) {
                adjacent = new Vertex(adjIdentifier);
                vertices.add(adjacent);
                verticesMap.put(adjIdentifier, adjacent);
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
        return search(verticesMap.get(startIdentifier), new SpecialStack<>());
    }

    public ArrayList<Vertex> bfs(String startIdentifier) {
        return search(verticesMap.get(startIdentifier), new LinkedList<>());
    }

    private ArrayList<Vertex> search(Vertex startVertex, Deque<Vertex> dataStructure) {
        ArrayList<Vertex> path = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        if (startVertex != null) {
            dataStructure.add(startVertex);
            visited.add(startVertex.getIdentifier());
            for (Vertex vertex : vertices) {
                while (!dataStructure.isEmpty()) {
                    Vertex current = dataStructure.pop();
                    for (Vertex neighbor : current.getAdjacents()) {
                        if (!visited.contains(neighbor.getIdentifier())) {
                            dataStructure.add(neighbor);
                            visited.add(neighbor.getIdentifier());
                        }
                    }
                    path.add(current);
                }
                if (!visited.contains(vertex.getIdentifier())) {
                    dataStructure.add(vertex);
                    visited.add(vertex.getIdentifier());
                }
            }
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

        stack.add(verticesMap.get(startIdentifier));
        visited.add(startIdentifier);
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            System.out.println(current);
            unstacked.push(current);
            stackSize = stack.size();
            for (Vertex neighbor : current.getAdjacents()) {
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
        System.out.println("Graph Actual Nodes : " + verticesMap.keySet());
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }
}
