package graphTheory.impl;

import java.util.ArrayList;
import java.util.List;

//稀疏图 邻接表
public class SparseGraph {
    private int n;
    private int m;
    private boolean direct;
    private List<Integer>[] g;

    public SparseGraph(int n, boolean direct) {
        this.n = n;
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
    }

    public void addEdge(int i, int j) {
        g[i].add(j);
        if (!direct)
            g[j].add(i);
        m++;
    }

    public boolean hasEdge(int i, int j) {
        check(i, j);
        return g[i].contains(j);
    }

    public Iterator<Integer> iterator(int v) {
        return new AdjIterator(this, v);
    }


    private void check(int i, int j) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Attention ! n = " + n + ", your i = " + i + ", j = " + j);
        }
    }

    private class AdjIterator implements Iterator<Integer> {
        private List<Integer> list;
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
        public Integer next() {
            i++;
            if (i >= list.size())
                throw new ArrayIndexOutOfBoundsException();
            else
                return list.get(i);
        }
    }

}
