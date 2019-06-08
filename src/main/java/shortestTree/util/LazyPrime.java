package shortestTree.util;

import shortestTree.Edge;
import shortestTree.Graph;
import shortestTree.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LazyPrime {
    private Graph graph;

    private List<Edge> shortestPath;

    private boolean[] visited;

    public LazyPrime(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.pointsCount()];
        Arrays.fill(visited, false);
        this.shortestPath = new ArrayList<>(graph.pointsCount() - 1);
        visited[0] = true;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        popInQueue(queue, 0);
        while (!queue.isEmpty()) {
            Edge edge = queue.remove();
            if (!isVisited(edge)) {
                this.shortestPath.add(edge);
                int nextPoint = visited[edge.getA()] ? edge.getB() : edge.getA();
                visited[nextPoint] = true;
                popInQueue(queue, nextPoint);
            } else {
                continue;
            }
        }
    }

    private void popInQueue(PriorityQueue<Edge> queue, int point) {
        Iterator iter = graph.iterator(point);
        while (iter.hasNext()) {
            Edge edge = iter.next();
            if (isVisited(edge))
                continue;
            else
                queue.add(edge);
        }
    }

    private boolean isVisited(Edge edge) {
        return visited[edge.getA()] && visited[edge.getB()];
    }

    public List<Edge> getShortestPath() {
        return this.shortestPath;
    }

    public double shortestLength() {
        double length = 0;
        for (Edge e : this.shortestPath) {
            length += e.getWeight();
        }
        return length;
    }

    public void showPath() {
        for (Edge e : this.shortestPath) {
            System.out.println(e.getA() + " -> " + e.getB() + " : " + e.getWeight());
        }
    }
}
