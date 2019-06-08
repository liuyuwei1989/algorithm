package shortestTree.util;

import shortestTree.Graph;

import java.io.BufferedInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ReadGraph {

    private String fileName;

    public ReadGraph(String fileName) {
        this.fileName = fileName;
    }

    public <T extends Graph> T generateGraph(Class<T> tClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T graph;
        try (Scanner scanner = new Scanner(new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream(fileName)), "UTF-8")) {
            int n = Integer.parseInt(scanner.next());
            int m, count = Integer.parseInt(scanner.next());
            m = count;
            graph = tClass.getConstructor(int.class, boolean.class).newInstance(n, false);
            for (; m > 0; m--) {
                graph.addEdge(Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()), Double.parseDouble("0" + scanner.next()));
            }
            if (m > 0 || graph.edgesCount() < count) {
                throw new IndexOutOfBoundsException("edge not enough : " + graph.edgesCount() + " : " + (count - m) + " : " + count);
            }
        }
        return graph;
    }

    public <T extends Graph> T generateDirectGraph(Class<T> tClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T graph;
        try (Scanner scanner = new Scanner(new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream(fileName)), "UTF-8")) {
            int n = Integer.parseInt(scanner.next());
            int m, count = Integer.parseInt(scanner.next());
            m = count;
            graph = tClass.getConstructor(int.class, boolean.class).newInstance(n, true);
            for (; m > 0; m--) {
                graph.addEdge(Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()), Double.parseDouble("0" + scanner.next()));
            }
            if (m > 0 || graph.edgesCount() < count) {
                throw new IndexOutOfBoundsException("edge not enough : " + graph.edgesCount() + " : " + (count - m) + " : " + count);
            }
        }
        return graph;
    }
}
