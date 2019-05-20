package graphTheory;

import graphTheory.impl.Iterator;

public abstract class Graph {
    protected int n;
    protected int m;
    protected boolean direct;

    public Graph(int n, boolean direct) {
        this.n = n;
        this.direct = direct;
    }

    public int count() {
        return m;
    }

    public abstract void addEdge(int i, int j);

    public abstract boolean hasEdge(int i, int j);

    public abstract Iterator<Integer> iterator(int v);

    public abstract void show();
}
