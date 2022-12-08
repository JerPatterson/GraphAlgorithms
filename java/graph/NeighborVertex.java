package graph;

public class NeighborVertex extends Vertex implements Comparable<NeighborVertex> {
    NeighborVertex(String identifier, Integer weightTo) {
        super(identifier);
        cost = weightTo;
    }

    private final Integer cost;
    public Integer getCostAwayFrom() { return cost; }


    @Override
    public int compareTo(NeighborVertex other) {
        return cost == null ? 0 : cost.compareTo(other.getCostAwayFrom());
    }
}
