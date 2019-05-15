package graphTheory.impl;

//稠密图 邻接矩阵
public class DenseGraph {
    private int n;
    private int m;

    private boolean g[][];
    private boolean direct;

    public DenseGraph(int n, boolean direct) {
        this.n = n;
        this.g = new boolean[n][n];
        this.direct = direct;
    }

    public void connect(int i, int j) {
        if (isConnected(i, j)) {
            return;
        }
        g[i][j] = true;
        m++;
        if (!direct) {
            g[j][i] = true;
        }

    }

    public int count() {
        return m;
    }

    public boolean isConnected(int i, int j) {
        check(i, j);
        return g[i][j];
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
        private boolean[] list;
        private int i;

        AdjIterator(DenseGraph graph, int v) {
            i = -1;
            list = graph.g[v];
        }

        @Override
        public boolean hasNext() {
            for (; i + 1 < list.length; i++) {
                if (list[i + 1]) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Integer next() {
            i++;
            for (; i < list.length; i++) {
                if (list[i]) {
                    return i;
                }
            }
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
