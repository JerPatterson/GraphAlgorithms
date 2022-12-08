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

    public void addEdge(Edge edge) {
        addNewVertex(edge.getEdge()[0].getIdentifier(),
                new String[] { edge.getEdge()[1].getIdentifier() });
    }

    private void addReversedEdge(Edge edge) {
        addNewVertex(edge.getEdge()[1].getIdentifier(),
                new String[] { edge.getEdge()[0].getIdentifier() });
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
        ArrayList<Vertex> path = new ArrayList<>();;
        if (startVertex != null) {
            HashSet<String> visited = new HashSet<>();
            dataStructure.add(startVertex);
            visited.add(startVertex.getIdentifier());
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
        }
        return path;
    }

    private ArrayList<Vertex> completeSearch(Vertex startVertex, Deque<Vertex> dataStructure) {
        ArrayList<Vertex> completePath = new ArrayList<>();
        dataStructure.add(startVertex);
        for (Vertex vertex : vertices) {
            completePath.addAll(search(dataStructure.pop(), dataStructure));
            if (!completePath.contains(vertex.getIdentifier())) {
                dataStructure.add(vertex);
            }
        }
        return completePath;
    }


    public LinkedList<Vertex> getDfsClosingTimes(String identifier) {
        Vertex startWanted = verticesMap.get(identifier);
        vertices.add(vertices.indexOf(startWanted), vertices.get(0));
        vertices.add(0, startWanted);
        return getDfsClosingTimes();
    }

    private LinkedList<Vertex> getDfsClosingTimes() {
        LinkedList<Vertex> closingTimes = new LinkedList<>();
        Stack<Vertex> stack = new Stack<>();
        HashSet<String> visited = new HashSet<>();

        boolean stackIncrease;
        Stack<Vertex> unstacked = new Stack<>();
        for (Vertex vertex : vertices) {
            if (!visited.contains(vertex.getIdentifier())) {
                stack.push(vertex);
                visited.add(vertex.getIdentifier());
            }
            while (!stack.isEmpty()) {
                Vertex current = stack.pop();
                unstacked.push(current);
                stackIncrease = false;
                for (Vertex neighbor : current.getAdjacents()) {
                    if (!visited.contains(neighbor.getIdentifier())) {
                        stack.add(neighbor);
                        stackIncrease = true;
                        visited.add(neighbor.getIdentifier());
                    }
                }
                if (!stackIncrease) {
                    closingTimes.push(unstacked.pop());
                }
            }
            while (!unstacked.isEmpty()) {
                closingTimes.push(unstacked.pop());
            }
        }
        return closingTimes;
    }

    public NoWeightGraph getTransposed() {
        NoWeightGraph transposedGraph = new NoWeightGraph();
        for (Edge edge : edges) {
            transposedGraph.addReversedEdge(edge);
        }
        return transposedGraph;
    }

    public ArrayList<ArrayList<String>> getStronglyConnectedComponents() {
        NoWeightGraph transposed = getTransposed();
        HashSet<String> visited = new HashSet<>();
        ArrayList<String> currentComponent;
        ArrayList<ArrayList<String>> connectedComponents = new ArrayList<>();
        for (Vertex vertex1 : transposed.getDfsClosingTimes()) {
            if (!visited.contains(vertex1.getIdentifier())) {
                currentComponent = new ArrayList<>();
                for (Vertex vertex2 : dfs(vertex1.getIdentifier())) {
                    currentComponent.add(vertex2.getIdentifier());
                    visited.add(vertex2.getIdentifier());
                }
                connectedComponents.add(currentComponent);
            }
        }
        return connectedComponents;
    }


    public void print() {
        System.out.println("Graph Actual Nodes : " + vertices);
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }
}
