package weightGraph.impl;

import weightGraph.Edge;
import weightGraph.Graph;
import weightGraph.Iterator;

//稠密图 邻接矩阵
public class DenseGraph extends Graph {


    private Edge g[][];


    public DenseGraph(int n, boolean direct) {
        super(n, direct);
        this.g = new Edge[n][n];
    }

    public void addEdge(int i, int j, double weight) {
        if (hasEdge(i, j)) {
            g[i][j] = null;
            m--;
            if (!direct) {
                g[j][i] = null;
            }
        }
        g[i][j] = new Edge(i, j, weight);
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
