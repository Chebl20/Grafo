package Grafo.src.Q4.grafo;

public class Aresta<T extends Comparable<T>> {
    private Vertice<T> vertice1;
    private Vertice<T> vertice2;

    public Aresta(Vertice<T> vertice1, Vertice<T> vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
    }

    public Vertice<T> getVertice1() {
        return vertice1;
    }

    public Vertice<T> getVertice2() {
        return vertice2;
    }

    @Override
    public String toString() {
        return vertice1 + " - " + vertice2;
    }
}