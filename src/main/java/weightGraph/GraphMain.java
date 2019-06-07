package weightGraph;

import heap.exception.OutOfHeapBoundException;
import weightGraph.impl.DenseGraph;
import weightGraph.impl.SparseGraph;
import weightGraph.util.BetterPrime;
import weightGraph.util.Krusk;
import weightGraph.util.LazyPrime;
import weightGraph.util.ReadGraph;

import java.lang.reflect.InvocationTargetException;

public class GraphMain {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, OutOfHeapBoundException {
        ReadGraph graph = new ReadGraph("WGraph1.txt");
        DenseGraph d_g_1 = graph.generateGraph(DenseGraph.class);
        SparseGraph s_g_1 = graph.generateGraph(SparseGraph.class);
        d_g_1.show();
        s_g_1.show();
        LazyPrime prime = new LazyPrime(d_g_1);
        prime.showPath();
        System.out.println(prime.shortestLength());
        BetterPrime prime1 = new BetterPrime(d_g_1);
        prime1.showPath();
        System.out.println(prime1.shortestLength());

        Krusk krusk = new Krusk(d_g_1);
        krusk.showPath();
        System.out.println(krusk.shortestLength());

    }
}
