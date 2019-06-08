package shortestTree.impl;

import shortestTree.Edge;
import shortestTree.Graph;
import shortestTree.Iterator;

import java.util.ArrayList;
import java.util.List;

//稠密图 邻接矩阵
public class DenseGraph extends Graph {


    private Edge g[][];

    private List<Edge> edges;


    public DenseGraph(int n, boolean direct) {
        super(n, direct);
        this.g = new Edge[n][n];
        edges = new ArrayList<>();
    }

    public void addEdge(int i, int j, double weight) {
        if (hasEdge(i, j)) {
            edges.remove(g[i][j]);
            g[i][j] = null;
            m--;
            if (!direct) {
                g[j][i] = null;
                edges.remove(g[j][i]);
            }
        }
        g[i][j] = new Edge(i, j, weight);
        edges.add(g[i][j]);
        m++;
        if (!direct) {
            g[j][i] = new Edge(j, i, weight);
        }

    }

    @Override
    public boolean hasEdge(int i, int j) {
        check(i, j);
        return g[i][j] != null;
    }

    @Override
    public Iterator iterator(int v) {
        return new AdjIterator(this, v);
    }

    @Override
    public void show() {
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                System.out.printf((g[i][j] == null ? 0 : g[i][j].getWeight()) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public List<Edge> allEdges() {
        return this.edges;
    }

    private void check(int i, int j) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Attention ! n = " + n + ", your i = " + i + ", j = " + j);
        }
    }

    private class AdjIterator implements Iterator {
        private Edge[] list;
        private int i;

        AdjIterator(DenseGraph graph, int v) {
            i = -1;
            list = graph.g[v];
        }

        @Override
        public boolean hasNext() {
            for (; i + 1 < list.length; i++) {
                if (list[i + 1] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Edge next() {
            i++;
            for (; i < list.length; i++) {
                if (list[i] != null) {
                    return list[i];
                }
            }
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
