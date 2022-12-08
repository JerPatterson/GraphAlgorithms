package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

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

    public void addNewVertex(String identifier, String[] adjacents, Integer[] weights) {
        Vertex current;
        current = verticesMap.get(identifier);
        if (current == null) {
            current = new Vertex(identifier);
            verticesMap.put(identifier, current);
        }

        int i = 0;
        Vertex adjacent;
        for (String adjIdentifier : adjacents) {
            adjacent = verticesMap.get(adjIdentifier);
            if (adjacent == null) {
                adjacent = new Vertex(adjIdentifier);
                verticesMap.put(adjIdentifier, adjacent);
            }

            current.addAdjacent(adjacent, weights[i]);
            edges.add(new Edge(current, adjacent, weights[i++]));
        }
    }


    public Graph prim() {
        Graph minimalWeightGraph = new Graph();
        HashSet<String> known = new HashSet<>();
        PriorityQueue<Edge> possibleEdges = new PriorityQueue<>();

        Edge cheapest;
        Vertex current = verticesMap.get(edges.peek().getEdge()[0].getIdentifier());
        while (known.size() != verticesMap.size() - 1) {
            known.add(current.getIdentifier());
            for (Vertex adjacent : current.getAdjacents()) {
                if (!known.contains(adjacent.getIdentifier())) {
                    possibleEdges.add(new Edge(current, adjacent, current.getCostToAdjacent(adjacent)));
                }
            }

            cheapest = possibleEdges.poll();
            while (known.contains(cheapest.getEdge()[1].getIdentifier())) {
                cheapest = possibleEdges.poll();
            }
            minimalWeightGraph.addNewVertex(cheapest.getEdge()[0].getIdentifier(),
                    new String[] { cheapest.getEdge()[1].getIdentifier() },
                    new Integer[] { cheapest.getCost() });
            current = verticesMap.get(cheapest.getEdge()[1].getIdentifier());
        }

        return minimalWeightGraph;
    }

    public Graph kruskal() {
        Graph minimalWeightGraph = new Graph();

        Edge cheapest;
        Vertex first, second;
        HashMap<String, Integer> used = new HashMap<>();
        PriorityQueue<Edge> edgesCopy = new PriorityQueue<>(edges);

        while (used.size() != verticesMap.size()) {
            cheapest = edgesCopy.poll();
            first = cheapest.getEdge()[0];
            second = cheapest.getEdge()[1];
            if ((used.get(first.getIdentifier()) == null || used.get(first.getIdentifier()).compareTo(2) < 0)
                    && (used.get(second.getIdentifier()) == null || used.get(second.getIdentifier()).compareTo(2) < 0)) {
                minimalWeightGraph.addNewVertex(first.getIdentifier(),
                        new String[] { second.getIdentifier() }, new Integer[] { cheapest.getCost() });

                used.put(first.getIdentifier(), used.get(first.getIdentifier()) != null ?
                        used.get(first.getIdentifier()) + 1 : 1);
                used.put(second.getIdentifier(), used.get(second.getIdentifier()) != null ?
                        used.get(second.getIdentifier()) + 1 : 1);
            }
        }

        return minimalWeightGraph;
    }
}
