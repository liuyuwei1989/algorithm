package graphTheory;

import graphTheory.impl.DenseGraph;
import graphTheory.impl.SparseGraph;
import graphTheory.util.Component;
import graphTheory.util.Path;
import graphTheory.util.ReadGraph;
import graphTheory.util.ShortestPath;

import java.lang.reflect.InvocationTargetException;

public class GraphMain {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ReadGraph graph1 = new ReadGraph("Graph1.txt");
        ReadGraph graph2 = new ReadGraph("Graph2.txt");
        ReadGraph graph3 = new ReadGraph("Graph3.txt");

        DenseGraph d_graph_1 = graph1.generateGraph(DenseGraph.class);
        System.out.println("d_graph_1 = " + d_graph_1);
        d_graph_1.show();
        DenseGraph d_graph_2 = graph2.generateGraph(DenseGraph.class);
        System.out.println("d_graph_2 = " + d_graph_2);
        d_graph_2.show();
        DenseGraph d_graph_3 = graph3.generateGraph(DenseGraph.class);
        System.out.println("d_graph_3 = " + d_graph_3);
        d_graph_3.show();

        SparseGraph s_graph_1 = graph1.generateGraph(SparseGraph.class);
        System.out.println("s_graph_1 = " + s_graph_1);
        s_graph_1.show();
        SparseGraph s_graph_2 = graph2.generateGraph(SparseGraph.class);
        System.out.println("s_graph_2 = " + s_graph_2);
        s_graph_2.show();
        SparseGraph s_graph_3 = graph3.generateGraph(SparseGraph.class);
        System.out.println("s_graph_3 = " + s_graph_3);
        s_graph_3.show();


        Component<DenseGraph> dense_1 = new Component<>(d_graph_1);
        System.out.println(dense_1.cCount());
        System.out.println(dense_1.connected(0, 5));
        System.out.println(dense_1.connected(3, 9));

        Component<SparseGraph> sparse_2 = new Component<>(s_graph_2);
        System.out.println(sparse_2.cCount());

        Path dense_path_3 = new Path(d_graph_3, 0);
        dense_path_3.showPath(6);

        Path sparse_path_3 = new Path(s_graph_3, 0);
        sparse_path_3.showPath(6);

        ShortestPath sparse_shortest_path_3 = new ShortestPath(s_graph_3, 0);
        sparse_shortest_path_3.showPath(6);

    }
}
