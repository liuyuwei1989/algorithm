package weightGraph;

import weightGraph.impl.DenseGraph;
import weightGraph.impl.SparseGraph;
import weightGraph.util.LazyPrime;
import weightGraph.util.ReadGraph;

import java.lang.reflect.InvocationTargetException;

public class GraphMain {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ReadGraph graph = new ReadGraph("WGraph1.txt");
        DenseGraph d_g_1 = graph.generateGraph(DenseGraph.class);
        SparseGraph s_g_1 = graph.generateGraph(SparseGraph.class);
        d_g_1.show();
        s_g_1.show();
        LazyPrime prime = new LazyPrime(d_g_1);
        prime.showPath();
        System.out.println(prime.shortestLength());
    }
}
