package graphTheory;

import graphTheory.impl.DenseGraph;
import graphTheory.impl.SparseGraph;
import graphTheory.util.ReadGraph;

import java.lang.reflect.InvocationTargetException;

public class GraphMain {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ReadGraph graph1 = new ReadGraph("Graph1.txt");
        ReadGraph graph2 = new ReadGraph("Graph2.txt");

        DenseGraph d_graph_1 = graph1.generateGraph(DenseGraph.class);
        System.out.println("d_graph_1 = " + d_graph_1);
        d_graph_1.show();
        DenseGraph d_graph_2 = graph2.generateGraph(DenseGraph.class);
        System.out.println("d_graph_2 = " + d_graph_2);
        d_graph_2.show();

        SparseGraph s_graph_1 = graph1.generateGraph(SparseGraph.class);
        System.out.println("s_graph_1 = " + s_graph_1);
        s_graph_1.show();
        SparseGraph s_graph_2 = graph2.generateGraph(SparseGraph.class);
        System.out.println("s_graph_2 = " + s_graph_2);
        s_graph_2.show();
    }
}
