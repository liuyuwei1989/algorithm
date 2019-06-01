package graphTheory.util;

import graphTheory.Graph;
import graphTheory.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPath {
    private Graph graph;
    private int start;

    private boolean[] visited;
    private int[] from;
    private int[] distance;
    private int n;

    public ShortestPath(Graph graph, int start) {
        this.graph = graph;
        this.start = start;
        this.visited = new boolean[graph.pointsCount()];
        this.distance = new int[graph.pointsCount()];
        this.from = new int[graph.pointsCount()];

        Arrays.fill(visited, false);
        Arrays.fill(distance, -1);
        Arrays.fill(from, -1);

        /*广度优先遍历*/
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        distance[start] = 0;
        visited[start] = true;

        while (!stack.empty()) {
            int s = stack.pop();
            Iterator iter = graph.iterator(s);
            while (iter.hasNext()) {
                int i = iter.next();
                if (!visited[i]) {
                    stack.push(i);
                    from[i] = s;
                    visited[i] = true;
                    distance[i] = distance[s] + 1;
                }
            }
        }
    }

    public List<Integer> path(int w) {
        Stack<Integer> tmp = new Stack<>();
        int p = w;
        tmp.push(w);
        while (from[p] != -1) {
            p = from[p];
            tmp.push(p);
        }
        List<Integer> path = new ArrayList<>();
        while (!tmp.empty()) {
            path.add(tmp.pop());
        }
        return path;
    }

    public void showPath(int w) {
        List<Integer> path = path(w);
        for (int i = 0; i < path.size(); i++) {
            if (i < path.size() - 1)
                System.out.print(path.get(i) + " -> ");
            else
                System.out.println(path.get(i));
        }
    }
}
