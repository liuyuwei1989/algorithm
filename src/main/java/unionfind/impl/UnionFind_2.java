package unionfind.impl;

public class UnionFind_2 {

    private int[] point;

    public UnionFind_2(int n) {
        this.point = new int[n];
        for (int i = 0; i < n; i++) {
            point[i] = i;
        }
    }

    public int findFather(int i) {
        while (point[i] != i) {
            i = point[i];
        }
        return i;
    }

    public void union(int i, int j) {
        int iFather = findFather(i);
        int jFather = findFather(j);
        point[iFather] = jFather;
    }

    public boolean isConnected(int i, int j) {
        return findFather(i) == findFather(j);
    }
}
