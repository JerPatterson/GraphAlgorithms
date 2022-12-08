package graph;

public class NeighborVertex extends Vertex implements Comparable<NeighborVertex> {
    NeighborVertex(Vertex vertex, Integer weightTo) {
        super(vertex);
        cost = weightTo;
    }

    private final Integer cost;
    public Integer getCostAwayFrom() { return cost; }


    @Override
    public int compareTo(NeighborVertex other) {
        return cost == null ? 0 : cost.compareTo(other.getCostAwayFrom());
    }
}
