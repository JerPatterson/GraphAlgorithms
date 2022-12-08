package graph;

public class Main {
    public static void main(String[] args) {
        NoWeightGraph graph0 = new NoWeightGraph();
        graph0.addNewVertex(1, new Integer[] {4, 3});
        graph0.addNewVertex(2, new Integer[] {1, 3, 4, 5, 7});
        graph0.addNewVertex(3, new Integer[] {4});
        graph0.addNewVertex(4, new Integer[] {1, 7, 2});
        graph0.addNewVertex(5, new Integer[] {3, 4});
        graph0.addNewVertex(6, new Integer[] {2});
        graph0.addNewVertex(7, new Integer[] {1, 2, 6, 7});
        graph0.addNewVertex(8, new Integer[] {9, 10});
        graph0.addNewVertex(9, new Integer[] {8, 10});
        graph0.addNewVertex(10, new Integer[] {});

        System.out.println(graph0);
        System.out.println();

        System.out.println(graph0.bfs());
        System.out.println(graph0.dfs());
        System.out.println(graph0.getDfsClosingTimes("1"));
        System.out.println(graph0.getTransposed().getDfsClosingTimes("1"));
        System.out.println(graph0.getStronglyConnectedComponents());
        System.out.println();


        Graph graph1 = new Graph();
        graph1.addNewVertex(1, new Integer[] {4, 3}, new Integer[] {24, 32});
        graph1.addNewVertex(2, new Integer[] {1, 3, 4, 5, 7}, new Integer[] {11, 31, 41, 52, 72});
        graph1.addNewVertex(3, new Integer[] {4}, new Integer[] {14});
        graph1.addNewVertex(4, new Integer[] {1, 7, 2}, new Integer[] {21, 27, 42});
        graph1.addNewVertex(5, new Integer[] {3, 4}, new Integer[] {3, 55});
        graph1.addNewVertex(6, new Integer[] {2}, new Integer[] {36});
        graph1.addNewVertex(7, new Integer[] {1, 2, 6, 7}, new Integer[] {17, 29, 69, 78});
    }
}
