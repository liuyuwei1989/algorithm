package unionfind.impl;

import java.util.Arrays;

public class UnionFind_4 {

    private int[] point;
    private int[] rank;

    public UnionFind_4(int n) {
        this.point = new int[n];
        for (int i = 0; i < n; i++) {
            point[i] = i;
        }
        this.rank = new int[n];
        Arrays.fill(rank, 1);
    }

    public int findFather(int i) {
        /*while (point[i] != i) {
            point[i] = point[point[i]];
            i = point[i];
        }
        return i;*/
        if (point[i] == i) {
            return i;
        } else {
            return point[i] = findFather(point[i]);
        }
    }

    public void union(int i, int j) {
        int iFather = findFather(i);
        int jFather = findFather(j);
        if (this.rank[iFather] < this.rank[jFather]) {
            this.point[iFather] = jFather;
        } else if (this.rank[jFather] < this.rank[iFather]) {
            this.point[jFather] = iFather;
        } else if (this.rank[jFather] == this.rank[iFather]) {
            this.rank[jFather] = iFather;
            this.rank[iFather] += 1;
        }
    }

    public boolean isConnected(int i, int j) {
        return findFather(i) == findFather(j);
    }
}
