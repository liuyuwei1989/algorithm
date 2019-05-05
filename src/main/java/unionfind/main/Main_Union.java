package unionfind.main;

import unionfind.impl.UnionFind_2;

public class Main_Union {
    public static void main(String[] args) {
        UnionFind_2 unionFind = new UnionFind_2(100);
        System.out.println(unionFind.isConnected(0, 99));
        unionFind.union(0,99);
        System.out.println(unionFind.isConnected(0,99));
        unionFind.union(2, 3);
        unionFind.union(2, 4);
        unionFind.union(9, 10);
        unionFind.union(10, 11);
        unionFind.union(11, 2);
        System.out.println(unionFind.isConnected(4, 9));
    }
}
