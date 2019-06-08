package shortestPath.util;

import shortestTree.Edge;
import shortestTree.Graph;
import shortestTree.Iterator;

/**
 * 含有负权边的最短路径算法
 */
public class BellmanFord {
    private Graph graph;
    private int start;
    private Double[] distance;
    private int[] from;
    private boolean hasNagetiveCycle;

    public BellmanFord(Graph graph, int start) {
        this.graph = graph;
        this.start = start;
        this.distance = new Double[this.graph.pointsCount()];
        this.from = new int[this.graph.pointsCount()];
        for (int i = 0; i < from.length; i++) {
            from[i] = i;
        }
        this.hasNagetiveCycle = false;
        distance[this.start] = 0.0;

        for (int i = 1; i < this.graph.pointsCount(); i++) {
            for (int point = 0; point < this.graph.pointsCount(); point++) {
                Iterator iter = this.graph.iterator(point);
                while (iter.hasNext()) {
                    Edge edge = iter.next();
                    int other = edge.other(point);
                    if (from[other] == other || distance[other] > distance[point] + edge.getWeight()) {
                        distance[other] = distance[point] + edge.getWeight();
                        from[other] = point;
                    }
                }
            }
        }
    }
}
