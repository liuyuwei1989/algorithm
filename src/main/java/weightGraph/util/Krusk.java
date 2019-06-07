package weightGraph.util;

import unionfind.impl.UnionFind_4;
import weightGraph.Edge;
import weightGraph.Graph;

import java.util.ArrayList;
import java.util.List;

public class Krusk {
    private Graph graph;
    private UnionFind_4 unionFind_4;
    private Edge[] edges;
    private List<Edge> shortestPath;

    public Krusk(Graph graph) {
        this.graph = graph;
        this.unionFind_4 = new UnionFind_4(this.graph.pointsCount());
        this.edges = new Edge[graph.edgesCount()];
        edges = graph.allEdges().toArray(edges);
        TripleQuick<Edge> sort = new TripleQuick<>();
        sort.sort(edges);
        shortestPath = new ArrayList<>();
        findPath();
    }

    private void findPath() {
        for (int i = 0; i < edges.length; i++) {
            Edge edge = edges[i];
            if (!unionFind_4.isConnected(edge.getA(), edge.getB())) {
                unionFind_4.union(edge.getA(),edge.getB());
                shortestPath.add(edge);
            }
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
