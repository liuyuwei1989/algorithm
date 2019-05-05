package unionfind.impl;

import java.util.Arrays;

public class UnionFind_3 {

    private int[] point;
    private int[] size;

    public UnionFind_3(int n) {
        this.point = new int[n];
        for (int i = 0; i < n; i++) {
            point[i] = i;
        }
        this.size = new int[n];
        Arrays.fill(size, 1);
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
        if (this.size[iFather] <= this.size[jFather]) {
            this.point[iFather] = jFather;
            this.size[jFather] += this.size[iFather];
        } else {
            this.point[jFather] = iFather;
            this.size[iFather] += this.size[jFather];
        }
    }

    public boolean isConnected(int i, int j) {
        return findFather(i) == findFather(j);
    }
}
