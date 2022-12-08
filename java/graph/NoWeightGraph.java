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

    private void setStartVertex(String identifier) {
        Vertex startWanted = verticesMap.get(identifier);
        if (vertices.indexOf(startWanted) != -1) {
            vertices.add(vertices.indexOf(startWanted), vertices.get(0));
            vertices.add(0, startWanted);
        }
    }


    public ArrayList<Vertex> dfs() {
        return dfs(vertices.get(0).getIdentifier(), true);
    }

    public ArrayList<Vertex> dfs(String startIdentifier, Boolean completeSearch) {
        class SpecialStack<AnyType> extends LinkedList<AnyType> {
            @Override
            public boolean add(AnyType anyType) {
                super.addFirst(anyType);
                return super.getFirst() == anyType;
            }
        }
        setStartVertex(startIdentifier);
        return search(new SpecialStack<>(), completeSearch);
    }


    public ArrayList<Vertex> bfs() {
        return bfs(vertices.get(0).getIdentifier(), true);
    }

    public ArrayList<Vertex> bfs(String startIdentifier, Boolean completeSearch) {
        setStartVertex(startIdentifier);
        return search(new LinkedList<>(), completeSearch);
    }


    private ArrayList<Vertex> search(Deque<Vertex> dataStructure, Boolean complete) {
        ArrayList<Vertex> path = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        for (Vertex vertex : (complete ? vertices : vertices.subList(0, 1))) {
            if (!visited.contains(vertex.getIdentifier())) {
                dataStructure.add(vertex);
                visited.add(vertex.getIdentifier());
            }
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


    public LinkedList<Vertex> getDfsClosingTimes(String identifier) {
        setStartVertex(identifier);
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
        for (Vertex vertex : vertices) {
            transposedGraph.addNewVertex(vertex.getIdentifier(), new String[] {});
        }
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
                for (Vertex vertex2 : dfs(vertex1.getIdentifier(), false)) {
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


    public String toString() {
        String str = "Graph Actual Nodes : " + vertices;
        int i = 1;
        for (Edge edge : edges) {
            str += edge + "  ";
            if (i++ % 3 == 0)
                str += "\n";
        }
        return str;
    }
}
