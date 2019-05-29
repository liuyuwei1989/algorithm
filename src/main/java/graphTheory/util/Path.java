package graphTheory.util;

import graphTheory.Graph;
import graphTheory.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Path {
    private Graph graph;
    private int s;
    private boolean[] visited;
    private int[] from;

    public Path(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        from = new int[graph.pointsCount()];
        visited = new boolean[this.graph.pointsCount()];
        Arrays.fill(visited, false);
        Arrays.fill(from, -1);
        deepFirst(s);
    }

    private void deepFirst(int i) {
        if (visited[i])
            return;
        else
            visited[i] = true;
        Iterator iterator = graph.iterator(i);
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (!visited[next]) {
                from[next] = i;
                deepFirst(next);
            } else
                continue;
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
