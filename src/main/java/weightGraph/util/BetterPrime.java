package weightGraph.util;

import heap.exception.OutOfHeapBoundException;
import heap.impl.MinIndexHeap;
import weightGraph.Edge;
import weightGraph.Graph;
import weightGraph.Iterator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BetterPrime {
    private Graph graph;
    private boolean[] visited;
    private MinIndexHeap<Double> weightIndexHeap;
    private Edge[] shortestEdges;

    public BetterPrime(Graph graph) throws OutOfHeapBoundException {
        this.graph = graph;
        visited = new boolean[graph.pointsCount()];
        weightIndexHeap = new MinIndexHeap<>(Double.class, graph.pointsCount());
        shortestEdges = new Edge[graph.pointsCount()];

        Arrays.fill(visited, false);
        Arrays.fill(shortestEdges, null);
        visit(0);
        while (!weightIndexHeap.isEmpty()) {
            int minIndex = weightIndexHeap.popMinIndex();
            visit(minIndex);
        }
    }

    private void visit(int point) throws OutOfHeapBoundException {
        visited[point] = true;
        Iterator iter = this.graph.iterator(point);
        while (iter.hasNext()) {
            Edge edge = iter.next();
            int other = edge.other(point);
            if (visited[other]) {
                continue;
            }
            if (this.shortestEdges[other] == null) {
                this.shortestEdges[other] = edge;
                this.weightIndexHeap.insertValue(other, edge.getWeight());
            } else if (edge.getWeight() < this.shortestEdges[other].getWeight()) {
                this.shortestEdges[other] = edge;
                this.weightIndexHeap.change(other, edge.getWeight());
            }
        }
    }

    public List<Edge> getShortestPath() {
        return Arrays.asList(this.shortestEdges);
    }

    public double shortestLength() {
        double length = 0.0;
        for (Edge e : this.shortestEdges) {
            if (e != null)
                length += e.getWeight();
        }
        return length;
    }

    public void showPath() {
        for (Edge e : this.shortestEdges) {
            if (e != null)
                System.out.println(e.getA() + " -> " + e.getB() + " : " + e.getWeight());
        }
    }
}
