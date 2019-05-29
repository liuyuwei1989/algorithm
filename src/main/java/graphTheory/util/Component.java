package graphTheory.util;

import graphTheory.Graph;
import graphTheory.Iterator;

import java.util.Arrays;

/**
 * 计算连通分量
 *
 * @param <T>
 */
public class Component<T extends Graph> {
    private T graph;
    private boolean[] visited;
    private int[] connected;
    private int count;

    public Component(T graph) {
        this.graph = graph;
        this.count = 0;
        connected = new int[graph.pointsCount()];
        visited = new boolean[this.graph.pointsCount()];
        Arrays.fill(visited, false);
        Arrays.fill(connected, -1);
    }

    public int cCount() {
        for (int i = 0; i < graph.pointsCount(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                connected[i] = count;
                deepFirst(i);
            } else
                continue;
            count++;
        }
        return count;
    }

    private void deepFirst(int i) {
        Iterator iterator = graph.iterator(i);
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (!visited[next]) {
                connected[i] = count;
                visited[next] = true;
                deepFirst(next);
            } else
                continue;
        }
    }

    public boolean connected(int i, int j) {
        return connected[i] == connected[j];
    }
}
