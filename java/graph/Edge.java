package graph;

public class Edge implements Comparable<Edge> {
    Edge(Vertex start, Vertex end) {
        edge[0] = start;
        edge[1] = end;
    }

    Edge(Vertex start, Vertex end, Integer weight) {
        cost = weight;
        edge[0] = start;
        edge[1] = end;
    }


    private Integer cost = 0;
    private Vertex[] edge = new Vertex[2];


    public Vertex[] getEdge() { return edge; }
    public Integer getCost() { return cost; }


    @Override
    public int compareTo(Edge other) {
        if (cost > 0) {
            return cost.compareTo(other.getCost());
        }
        return (edge[0].getIdentifier() + edge[1].getIdentifier())
                .compareTo((other.getEdge()[0].getIdentifier() + other.getEdge()[1].getIdentifier()));
    }


    public void print() {
        if (cost == 0) {
            System.out.println("< " + edge[0].getIdentifier() +
                    " , " + edge[1].getIdentifier() + " >");
        } else {
            System.out.println("< " + edge[0].getIdentifier() +
                    " , " + edge[1].getIdentifier() + " | " + cost + " >");
        }
    }

    public String toString() {
        if (cost == 0) {
            return "< " + edge[0].getIdentifier() + " , " + edge[1].getIdentifier() + " >";
        }
        return "< " + edge[0].getIdentifier() + " , " + edge[1].getIdentifier() + " | " + cost + " >";
    }
}
