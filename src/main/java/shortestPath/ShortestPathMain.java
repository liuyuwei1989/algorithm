package shortestPath;

import shortestPath.util.BellmanFord;
import shortestPath.util.ShortestPath;
import shortestTree.impl.SparseGraph;
import shortestTree.util.ReadGraph;

public class ShortestPathMain {
    public static void main(String[] args) throws Exception {
        ReadGraph graph = new ReadGraph("WGraph2.txt");
        SparseGraph s_p_2 = graph.generateGraph(SparseGraph.class);

        ShortestPath path = new ShortestPath(s_p_2, 0);
        for (int i = 1; i < s_p_2.pointsCount(); i++) {
            if (path.isConnect(i)) {
                System.out.println("0 to " + i + " : " + path.distance(i));
                path.showPath(i);
            }
        }

        ReadGraph graph1 = new ReadGraph("WGraph3.txt");
        SparseGraph s_p_3 = graph1.generateDirectGraph(SparseGraph.class);

        BellmanFord bellmanFord = new BellmanFord(s_p_3, 0);
    }
}
