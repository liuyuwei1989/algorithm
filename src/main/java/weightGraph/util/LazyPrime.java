package weightGraph.util;

import weightGraph.Edge;
import weightGraph.Graph;
import weightGraph.Iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LazyPrime {
    private Graph graph;

    private List<Edge> shortestPath;

    public LazyPrime(Graph graph) {
        this.graph = graph;
        this.shortestPath = new ArrayList<>(graph.pointsCount() - 1);
        List<Integer> greenGroup = new ArrayList<>();
        List<Integer> blueGroup = new ArrayList<>();
        for (int i = 0; i < graph.pointsCount(); i++) {
            blueGroup.add(i);
        }
        greenGroup.add(blueGroup.remove(0));
        while (blueGroup.size() != 0) {
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            for (Integer i : greenGroup) {
                Iterator iter = graph.iterator(i);
                while (iter.hasNext()) {
                    Edge edge = iter.next();
                    queue.add(edge);
                }
            }
            Edge edge = queue.remove();
            while (greenGroup.contains(edge.getA()) && greenGroup.contains(edge.getB())) {
                edge = queue.remove();
            }
            int point = greenGroup.contains(edge.getA()) ? edge.getB() : edge.getA();
            greenGroup.add(point);
            blueGroup.remove(blueGroup.indexOf(point));
            this.shortestPath.add(edge);
        }
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
