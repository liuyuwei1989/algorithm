package unionfind.impl;

public class UnionFind_1 {
    private int[] data;
    private int count;

    public UnionFind_1(int n) {
        this.data = new int[n];
        this.count = n;
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
    }

    public void union(int p, int q) {
        int tmpQ = data[q];
        int tmpP = data[p];
        if (tmpP == tmpQ) {
            return;
        } else {
            data[q] = data[p];
            for (int i = 0; i < data.length; i++) {
                if (data[i] == tmpQ) {
                    data[i] = tmpP;
                }
            }
        }

    }

    public int find(int p) {
        return data[p];
    }

    public boolean isConnected(int p, int q) {
        return data[p] == data[q];
    }
}
