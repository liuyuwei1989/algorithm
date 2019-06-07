package weightGraph;

import java.util.List;

public abstract class Graph {
    protected int n;
    protected int m;
    protected boolean direct;

    public Graph(int n, boolean direct) {
        this.n = n;
        this.direct = direct;
    }

    public int edgesCount() {
        return m;
    }

    public int pointsCount() {
        return n;
    }

    public abstract void addEdge(int i, int j, double weight);

    public abstract boolean hasEdge(int i, int j);

    public abstract Iterator iterator(int v);

    public abstract void show();

    public abstract List<Edge> allEdges();
}
