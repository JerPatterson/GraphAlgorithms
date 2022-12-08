package graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addNewVertex(1, new Integer[] {4, 3}, new Integer[] {24, 32});
        graph.addNewVertex(2, new Integer[] {1, 3, 4, 5, 7}, new Integer[] {11, 31, 41, 52, 72});
        graph.addNewVertex(3, new Integer[] {4}, new Integer[] {14});
        graph.addNewVertex(4, new Integer[] {1, 7, 2}, new Integer[] {21, 27, 42});
        graph.addNewVertex(5, new Integer[] {3, 4}, new Integer[] {3, 55});
        graph.addNewVertex(6, new Integer[] {2}, new Integer[] {36});
        graph.addNewVertex(7, new Integer[] {1, 2, 6, 7}, new Integer[] {17, 29, 69, 78});

        graph.print();
        System.out.println();

        graph.prim().print();
        System.out.println();

        graph.kruskal().print();
        System.out.println();
    }
}
