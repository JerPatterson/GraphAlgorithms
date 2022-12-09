package graph;

import java.util.*;

public class WeightedGraph extends UnweightedGraph {
    WeightedGraph() {
        super();
    }


    @Override
    public void addNewVertex(Integer identifier, Integer[] adjacents) {
        int i = 0;
        Integer[] weights = new Integer[adjacents.length];
        String[] newAdjacents = new String[adjacents.length];
        for (Integer adjIdentifier : adjacents) {
            weights[i] = (int) Math.random() * 100;
            newAdjacents[i++] = adjIdentifier.toString();
        }
        addNewVertex(identifier.toString(), newAdjacents, weights);
    }

    @Override
    public void addEdge(Edge edge) {
        addNewVertex(edge.getEdge()[0].getIdentifier(),
                new String[] { edge.getEdge()[1].getIdentifier() },
                new Integer[] { edge.getCost() });
    }

    public void addNewVertex(Integer identifier, Integer[] adjacents, Integer[] weights) {
        int i = 0;
        String[] newAdjacents = new String[adjacents.length];
        for (Integer adjIdentifier : adjacents) {
            newAdjacents[i++] = adjIdentifier.toString();
        }
        addNewVertex(identifier.toString(), newAdjacents, weights);
    }

    protected void addNewVertex(String identifier, String[] adjacents, Integer[] weights) {
        Vertex current = verticesMap.get(identifier);
        if (current == null) {
            current = new Vertex(identifier);
            vertices.add(current);
            verticesMap.put(identifier, current);
        }
        int i = 0;
        Vertex adjacent;
        for (String adjIdentifier : adjacents) {
            adjacent = verticesMap.get(adjIdentifier);
            if (adjacent == null) {
                adjacent = new Vertex(adjIdentifier);
                vertices.add(adjacent);
                verticesMap.put(adjIdentifier, adjacent);
            }
            current.addAdjacent(adjacent, weights[i]);
            edges.add(new Edge(current, adjacent, weights[i++]));
        }
    }


    public WeightedGraph prim() {
        WeightedGraph minimalWeightTree = new WeightedGraph();

        Edge cheapest;
        Vertex current = edges.peek().getEdge()[0];
        HashSet<String> known = new HashSet<>();
        PriorityQueue<Edge> accessible = new PriorityQueue<>();
        while (known.size() != vertices.size() - 1) {
            known.add(current.getIdentifier());
            for (NeighborVertex adjacent : current.getAdjacents()) {
                if (!known.contains(adjacent.getIdentifier())) {
                    accessible.add(new Edge(current, adjacent, adjacent.getCostAway()));
                }
            }
            cheapest = accessible.poll();
            while (cheapest != null && known.contains(cheapest.getEdge()[1].getIdentifier())) {
                cheapest = accessible.poll();
            }
            if (cheapest != null) {
                minimalWeightTree.addEdge(cheapest);
                current = cheapest.getEdge()[1];
            } else {
                break;
            }
        }
        return minimalWeightTree;
    }


    public WeightedGraph kruskal() {
        WeightedGraph minimalWeightTree = new WeightedGraph();

        Edge cheapest;
        String first, second;
        HashSet<String> tempUnion;
        HashMap<String, Integer> used = new HashMap<>();
        HashMap<Integer, HashSet<String>> unions = new HashMap<>();
        PriorityQueue<Edge> edgesCopy = new PriorityQueue<>(edges);
        while (used.size() != vertices.size()) {
            cheapest = edgesCopy.poll();
            if (cheapest != null) {
                first = cheapest.getEdge()[0].getIdentifier();
                second = cheapest.getEdge()[1].getIdentifier();
                if (used.get(first) == null || used.get(second) == null) {
                    minimalWeightTree.addEdge(cheapest);
                    if (used.get(first) != null) {
                        used.put(second, used.get(first));
                        tempUnion = unions.get(used.get(first));
                        tempUnion.add(second);
                        unions.put(used.get(first), tempUnion);
                    } else if (used.get(second) != null) {
                        used.put(first, used.get(second));
                        tempUnion = unions.get(used.get(second));
                        tempUnion.add(first);
                        unions.put(used.get(second), tempUnion);
                    } else {
                        tempUnion = new HashSet<>();
                        used.put(first, first.hashCode());
                        used.put(second, first.hashCode());
                        tempUnion.add(first);
                        tempUnion.add(second);
                        unions.put(first.hashCode(), tempUnion);
                    }
                } else if (used.get(first).compareTo(used.get(second)) != 0) {
                    minimalWeightTree.addEdge(cheapest);
                    tempUnion = unions.get(used.get(first));
                    tempUnion.addAll(unions.get(used.get(second)));
                    unions.put(used.get(first), tempUnion);
                    for (String id : unions.get(used.get(second))) {
                        used.put(id, used.get(first));
                    }
                    unions.remove(second);
                }
            } else {
                break;
            }
        }
        return minimalWeightTree;
    }
}
