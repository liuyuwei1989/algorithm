package graphTheory.util;

import graphTheory.Graph;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Scanner;

public class ReadGraph {

    private String fileName;

    public ReadGraph(String fileName) {
        this.fileName = fileName;
    }

    public <T extends Graph> T generateGraph(Class<T> tClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T graph;
        try (Scanner scanner = new Scanner(new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream(fileName)), "UTF-8") ) {
            int n = Integer.parseInt(scanner.next());
            int m, count = Integer.parseInt(scanner.next());
            m = count;
            graph = tClass.getConstructor(int.class, boolean.class).newInstance(n, false);
            for (; m > 0; m--) {
                graph.addEdge(Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()));
            }
            if (m > 0 || graph.count() < count) {
                throw new IndexOutOfBoundsException("edge not enough : " + graph.count() + " : " + (count - m) + " : " + count);
            }
        }
        return graph;
    }
}
