package shortestPath.util;

import shortestTree.Edge;
import shortestTree.Graph;
import shortestTree.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 含有负权边的最短路径算法
 */
public class BellmanFord {
    private Graph graph;
    private int start;
    private Double[] distance;
    private Integer[] from;
    private boolean hasNagetiveCycle;

    public BellmanFord(Graph graph, int start) {
        this.graph = graph;
        this.start = start;
        this.distance = new Double[this.graph.pointsCount()];
        this.from = new Integer[this.graph.pointsCount()];
        Arrays.fill(distance, null);
        Arrays.fill(from, null);
        this.hasNagetiveCycle = false;
        distance[this.start] = 0.0;

        for (int i = 1; i < this.graph.pointsCount(); i++) {
            for (int point = 0; point < this.graph.pointsCount(); point++) {
                Iterator iter = this.graph.iterator(point);
                while (iter.hasNext()) {
                    Edge edge = iter.next();
                    int other = edge.other(point);
                    if ((from[point] != null || point == this.start) && (from[other] == null || distance[other] > distance[point] + edge.getWeight())) {
                        distance[other] = distance[point] + edge.getWeight();
                        from[other] = point;
                    }
                }
            }
        }
        this.hasNagetiveCycle = this.detectNegativeCycle();
    }

    // 判断图中是否有负权环
    private boolean detectNegativeCycle() {

        for (int point = 0; point < graph.pointsCount(); point++) {
            Iterator iter = this.graph.iterator(point);
            while (iter.hasNext()) {
                Edge edge = iter.next();
                int other = edge.other(point);
                if (from[point] != null && (from[other] == null || distance[other] > distance[point] + edge.getWeight())) {
                    return true;
                }
            }
        }

        return false;
    }

    public double distance(int other) {
        return distance[other];
    }

    public List<Integer> path(int w) throws Exception {
        throwError();
        Stack<Integer> tmp = new Stack<>();
        int p = w;
        tmp.push(w);
        while (from[p] != null) {
            p = from[p];
            tmp.push(p);
        }
        List<Integer> path = new ArrayList<>();
        while (!tmp.empty()) {
            path.add(tmp.pop());
        }
        return path;
    }

    public void showPath(int w) throws Exception {
        throwError();
        List<Integer> path = path(w);
        for (int i = 0; i < path.size(); i++) {
            if (i < path.size() - 1)
                System.out.print(path.get(i) + " -> ");
            else
                System.out.println(path.get(i));
        }
    }

    public boolean negativeCycle() {
        return this.hasNagetiveCycle;
    }

    private void throwError() throws Exception {
        if (this.hasNagetiveCycle) {
            throw new Exception("has nagetive cycle");
        }
    }

    public boolean isConnect(int other) {
        return from[other] != null;
    }
}
