package graph;

public class Graph extends NoWeightGraph {
    Graph() {
        super();
    }

    @Override
    public void addNewVertex(Integer identifier, Integer[] adjacents) {
        int i = 0;
        Integer[] weights = new Integer[adjacents.length];
        String[] newAdjacents = new String[adjacents.length];
        for (Integer adjIdentifier : adjacents) {
            weights[i] = 0;
            newAdjacents[i++] = adjIdentifier.toString();
        }
        addNewVertex(identifier.toString(), newAdjacents, weights);
    }

    public void addNewVertex(Integer identifier, Integer[] adjacents, Integer[] weights) {
        int i = 0;
        String[] newAdjacents = new String[adjacents.length];
        for (Integer adjIdentifier : adjacents) {
            newAdjacents[i++] = adjIdentifier.toString();
        }
        addNewVertex(identifier.toString(), newAdjacents, weights);
    }

    private void addNewVertex(String identifier, String[] adjacents, Integer[] weights) {
        Vertex current;
        current = vertex.get(identifier);
        if (current == null) {
            current = new Vertex(identifier);
            vertex.put(identifier, current);
        }

        int i = 0;
        Vertex adjacent;
        for (String adjIdentifier : adjacents) {
            adjacent = vertex.get(adjIdentifier);
            if (adjacent == null) {
                adjacent = new Vertex(adjIdentifier);
                vertex.put(adjIdentifier, adjacent);
            }

            current.addAdjacent(adjacent, weights[i]);
            edges.add(new Edge(current, adjacent, weights[i++]));
        }
    }
}
