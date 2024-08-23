package Grafo.src.Q6.grafo;

public class Aresta<T extends Comparable<T>> {
    private Vertice<T> vertice1;
    private Vertice<T> vertice2;
    private double peso;

    public Aresta(Vertice<T> vertice1, Vertice<T> vertice2, double peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    public Vertice<T> getVertice1() {
        return vertice1;
    }

    public Vertice<T> getVertice2() {
        return vertice2;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return vertice1 + " -(" + peso + ")-> " + vertice2;
    }
}