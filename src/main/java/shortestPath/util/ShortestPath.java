package shortestPath.util;

import heap.impl.MinIndexHeap;
import shortestTree.Edge;
import shortestTree.Graph;
import shortestTree.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPath {
    private Graph graph;
    private MinIndexHeap<Double> indexHeap;
    private int[] from;
    private Double[] distance;
    private boolean[] marked;
    private int start;

    public ShortestPath(Graph graph, int start) throws Exception {
        this.start = start;
        this.graph = graph;
        this.distance = new Double[this.graph.pointsCount()];
        this.from = new int[this.graph.pointsCount()];
        for (int i = 0; i < this.from.length; i++) {
            this.from[i] = i;
        }
        Arrays.fill(distance, null);
        indexHeap = new MinIndexHeap<>(Double.class, graph.pointsCount());
        this.marked = new boolean[this.graph.pointsCount()];
        Arrays.fill(marked, false);
        calculatePath();
    }

    private void calculatePath() throws Exception {
        init();
        while (!this.indexHeap.isEmpty()) {
            int point = indexHeap.popMinIndex();
            dealWith(point);
        }
    }

    private void dealWith(int point) throws Exception {
        Iterator iter = this.graph.iterator(point);
        while (iter.hasNext()) {
            Edge edge = iter.next();
            int other = edge.other(point);
            if (!marked[other]) {
                marked[other] = true;
                from[other] = point;
                this.indexHeap.insertValue(other, edge.getWeight() + distance[point]);
                distance[other] = edge.getWeight() + distance[point];
            } else {
                double newDistance = edge.getWeight() + distance[point];
                if (newDistance < distance[other]) {
                    from[other] = point;
                    this.indexHeap.change(other, newDistance);
                    distance[other] = newDistance;
                } else {
                    continue;
                }
            }
        }
    }

    private void init() throws Exception {
        Iterator iter = this.graph.iterator(start);
        distance[start] = 0.0;
        marked[start] = true;
        while (iter.hasNext()) {
            Edge edge = iter.next();
            int other = edge.other(start);
            marked[other] = true;
            from[other] = start;
            distance[other] = edge.getWeight();
            this.indexHeap.insertValue(other, edge.getWeight());
        }
    }

    public boolean isConnect(int other) {
        return marked[other];
    }

    public double distance(int other) {
        return distance[other];
    }

    public List<Integer> path(int w) {
        Stack<Integer> tmp = new Stack<>();
        int p = w;
        tmp.push(w);
        while (from[p] != p) {
            p = from[p];
            tmp.push(p);
        }
        List<Integer> path = new ArrayList<>();
        while (!tmp.empty()) {
            path.add(tmp.pop());
        }
        return path;
    }

    public void showPath(int w) {
        List<Integer> path = path(w);
        for (int i = 0; i < path.size(); i++) {
            if (i < path.size() - 1)
                System.out.print(path.get(i) + " -> ");
            else
                System.out.println(path.get(i));
        }
    }
}
