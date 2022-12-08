package graph;

public class Edge implements Comparable<Edge> {
    Edge(Vertex start, Vertex end) {
        first = start;
        second = end;
    }

    Edge(Vertex start, Vertex end, Integer weight) {
        cost = weight;
        first = start;
        second = end;
    }


    private Integer cost = null;
    private final Vertex first;
    private final Vertex second;


    public Vertex[] getEdge() { return new Vertex[] {first, second}; }
    public Integer getCost() { return cost; }


    @Override
    public int compareTo(Edge other) {
        if (cost != null) {
            return cost.compareTo(other.getCost());
        }
        return (first.getIdentifier() + first.getIdentifier())
                .compareTo((other.getEdge()[0].getIdentifier() + other.getEdge()[1].getIdentifier()));
    }

    public String toString() {
        return "< " + first + " , " + second + (cost == null ? " >" : " | " + cost + " >");
    }
}
