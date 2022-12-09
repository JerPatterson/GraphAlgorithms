package graph;

public class Main {
    public static void main(String[] args) {
        System.out.println("Unweighted Graph Test :");

        UnweightedGraph graph0 = new UnweightedGraph();
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

        System.out.println("BFS : " + graph0.bfs());
        System.out.println("DFS : " + graph0.dfs());
        System.out.println("DFS closing times: " +
                graph0.getDfsClosingTimes("1"));
        System.out.println("DFS closing times (transposed) : " +
                graph0.getTransposed().getDfsClosingTimes("1"));
        System.out.println("Strongly connected components : " +
                graph0.getStronglyConnectedComponents());
        System.out.println("Strongly connected components (transposed) : " +
                graph0.getTransposed().getStronglyConnectedComponents());
        System.out.println("Topological Sort : " +
                graph0.topologicalSort());
        System.out.println();
        System.out.println();


        System.out.println("Weighted Graph Test :");

        WeightedGraph graph1 = new WeightedGraph();
        graph1.addNewVertex(1, new Integer[] {4, 3}, new Integer[] {24, 32});
        graph1.addNewVertex(2, new Integer[] {1, 3, 4, 5, 7}, new Integer[] {11, 31, 41, 52, 72});
        graph1.addNewVertex(3, new Integer[] {4}, new Integer[] {14});
        graph1.addNewVertex(4, new Integer[] {1, 7, 2}, new Integer[] {21, 27, 42});
        graph1.addNewVertex(5, new Integer[] {3, 4}, new Integer[] {3, 55});
        graph1.addNewVertex(6, new Integer[] {2}, new Integer[] {36});
        graph1.addNewVertex(7, new Integer[] {1, 2, 6, 7}, new Integer[] {17, 29, 69, 78});
        graph1.addNewVertex(8, new Integer[] {9, 10}, new Integer[] {99, 10});
        graph1.addNewVertex(9, new Integer[] {8, 10}, new Integer[] {79, 10});
        graph1.addNewVertex(10, new Integer[] {});

        System.out.println(graph1);
        System.out.println();

        System.out.println("BFS : " + graph1.bfs());
        System.out.println("DFS : " + graph1.dfs());
        System.out.println("DFS closing times : " +
                graph1.getDfsClosingTimes("1"));
        System.out.println("DFS closing times (transposed) : " +
                graph1.getTransposed().getDfsClosingTimes("1"));
        System.out.println("Strongly connected components : " +
                graph1.getStronglyConnectedComponents());
        System.out.println("Strongly connected components (transposed) : " +
                graph1.getTransposed().getStronglyConnectedComponents());
        System.out.println("Topological Sort : " +
                graph1.topologicalSort());
        System.out.println();

        System.out.println("Kruskal reduced graph : " + graph1.kruskal());
        System.out.println("Prim reduced graph : " + graph1.prim());
        System.out.println("Dijkstra smallest paths : \n" + graph1.dijkstra("1"));
        System.out.println();
        System.out.println();


        System.out.println("Undirected Weighted Graph Test :");

        UndirectedWeightedGraph graph2 = new UndirectedWeightedGraph();
        graph2.addNewVertex(1, new Integer[] {4, 3}, new Integer[] {4, 32});
        graph2.addNewVertex(2, new Integer[] {1, 3, 5}, new Integer[] {11, 39, 52});
        graph2.addNewVertex(3, new Integer[] {4}, new Integer[] {41});
        graph2.addNewVertex(4, new Integer[] {7, 2}, new Integer[] {28, 42});
        graph2.addNewVertex(5, new Integer[] {3, 4}, new Integer[] {3, 35});
        graph2.addNewVertex(6, new Integer[] {2}, new Integer[] {58});
        graph2.addNewVertex(7, new Integer[] {1, 2, 6, 7}, new Integer[] {17, 29, 44, 78});

        System.out.println(graph2);
        System.out.println();

        System.out.println("BFS : " + graph2.bfs());
        System.out.println("DFS : " + graph2.dfs());
        System.out.println("DFS closing times : " +
                graph2.getDfsClosingTimes("1"));
        System.out.println("DFS closing times (transposed) : " +
                graph2.getTransposed().getDfsClosingTimes("1"));
        System.out.println("Strongly connected components : " +
                graph2.getStronglyConnectedComponents());
        System.out.println("Strongly connected components (transposed) : " +
                graph2.getTransposed().getStronglyConnectedComponents());
        System.out.println("Topological Sort : " +
                graph2.topologicalSort());
        System.out.println();

        System.out.println("Kruskal reduced graph : " + graph2.kruskal());
        System.out.println("Prim reduced graph : " + graph2.prim());
        System.out.println("Dijkstra smallest paths : \n" + graph2.dijkstra("1"));
        System.out.println();
        System.out.println();


        System.out.println("DAG Test :");

        WeightedGraph dag = new WeightedGraph();
        dag.addNewVertex(1, new Integer[] {4, 2}, new Integer[] {32, 20});
        dag.addNewVertex(2, new Integer[] {3}, new Integer[] {11});
        dag.addNewVertex(3, new Integer[] {4, 5}, new Integer[] {41, 57});
        dag.addNewVertex(4, new Integer[] {7}, new Integer[] {28});
        dag.addNewVertex(5, new Integer[] {6}, new Integer[] {35});
        dag.addNewVertex(6, new Integer[] {}, new Integer[] {});
        dag.addNewVertex(7, new Integer[] {6}, new Integer[] {17});

        System.out.println(dag);
        System.out.println(dag.getTransposed());
        System.out.println();

        System.out.println("BFS : " + dag.bfs());
        System.out.println("DFS : " + dag.dfs());
        System.out.println("DFS (transposed) : " + dag.getTransposed().dfs());
        System.out.println("DFS closing times : " +
                dag.getDfsClosingTimes("1"));
        System.out.println("DFS closing times (transposed) : " +
                dag.getTransposed().getDfsClosingTimes("1"));
        System.out.println("Strongly connected components : " +
                dag.getStronglyConnectedComponents());
        System.out.println("Strongly connected components (transposed) : " +
                dag.getTransposed().getStronglyConnectedComponents());
        System.out.println("Topological Sort : " +
                dag.topologicalSort());
        System.out.println();

        System.out.println("Kruskal reduced graph : " + dag.kruskal());
        System.out.println("Prim reduced graph : " + dag.prim());
        System.out.println("Dijkstra smallest paths : \n" + dag.dijkstra("1"));
        System.out.println();
        System.out.println();
    }
}
