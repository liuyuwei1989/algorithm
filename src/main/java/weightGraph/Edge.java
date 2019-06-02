package weightGraph;

import java.util.Objects;

public class Edge {
    private int a, b;
    private double weight;

    public Edge(int a, int b, double weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return a == edge.a &&
                b == edge.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
