package graph;

public class Main {
    public static void main(String[] args) {
        NoWeightGraph graph = new NoWeightGraph();

        graph.addNewVertex(1, new Integer[] {3, 4});
        graph.addNewVertex(2, new Integer[] {1, 3, 4, 5, 7});
        graph.addNewVertex(3, new Integer[] {4});
        graph.addNewVertex(4, new Integer[] {1, 2, 7});
        graph.addNewVertex(7, new Integer[] {1, 2, 6, 7});

        graph.print();
    }
}
