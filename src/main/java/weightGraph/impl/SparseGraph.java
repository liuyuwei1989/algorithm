package weightGraph.impl;

import weightGraph.Edge;
import weightGraph.Graph;
import weightGraph.Iterator;

import java.util.ArrayList;
import java.util.List;

//稀疏图 邻接表
public class SparseGraph extends Graph {


    private List<Edge>[] g;

    public SparseGraph(int n, boolean direct) {
        super(n, direct);
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
    }

    @Override
    public void addEdge(int i, int j, double weight) {
        g[i].remove(new Edge(i, j, 0));
        if (!direct) {
            g[j].remove(new Edge(j, i, 0));
        }

        g[i].add(new Edge(i, j, weight));
        if (!direct)
            g[j].add(new Edge(j, i, weight));
        m++;
    }

    @Override
    public boolean hasEdge(int i, int j) {
        check(i, j);
        return g[i].contains(j);
    }

    @Override
    public Iterator iterator(int v) {
        return new AdjIterator(this, v);
    }

    @Override
    public void show() {
        for (int i = 0; i < g.length; i++) {
            System.out.printf(i + " : ");
            for (int j = 0; j < g[i].size(); j++) {
                System.out.printf( "To:" + g[i].get(j).getB() +" wt:"  + g[i].get(j).getWeight() + " ");
            }
            System.out.println();
        }
    }


    private void check(int i, int j) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Attention ! n = " + n + ", your i = " + i + ", j = " + j);
        }
    }

    private class AdjIterator implements Iterator {
        private List<Edge> list;
        private int i;

        AdjIterator(SparseGraph graph, int v) {
            i = -1;
            list = graph.g[v];
        }

        @Override
        public boolean hasNext() {
            return i + 1 < list.size();
        }

        @Override
        public Edge next() {
            i++;
            if (i >= list.size())
                throw new ArrayIndexOutOfBoundsException();
            else
                return list.get(i);
        }
    }

}
