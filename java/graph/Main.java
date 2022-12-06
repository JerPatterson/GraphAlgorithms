package graph;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        NoWeightGraph graph = new NoWeightGraph();

        graph.addNewVertex(1, new Integer[] {4, 3});
        graph.addNewVertex(2, new Integer[] {1, 3, 4, 5, 7});
        graph.addNewVertex(3, new Integer[] {4});
        graph.addNewVertex(4, new Integer[] {1, 7, 2});
        graph.addNewVertex(7, new Integer[] {1, 2, 6, 7});

        graph.print();
        System.out.println();

        System.out.println("<- Graph Transposed ->");
        graph.getTransposed().print();
        System.out.println();

        System.out.println("Strongly Connected Components Count : " +
                graph.getStronglyConnectedComponents().size());
        System.out.println("Strongly Connected Components : " +
                graph.getStronglyConnectedComponents());
        System.out.println();

        ArrayList<Vertex> dfsPath = graph.dfs("1");
        System.out.println("DFS : " + dfsPath);

        ArrayList<Vertex> bfsPath = graph.bfs("1");
        System.out.println("BFS : " + bfsPath);
    }
}
