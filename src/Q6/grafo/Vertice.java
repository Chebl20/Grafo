package Grafo.src.Q6.grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Vertice<T extends Comparable<T>> {
    private T valor;
    private List<Aresta<T>> adjacentes; // Agora mantém uma lista de arestas
    private int tempoChegada;
    private int tempoPartida;

    public Vertice(T valor) {
        this.valor = valor;
        this.adjacentes = new ArrayList<>();
        this.tempoChegada = -1;
        this.tempoPartida = -1;
    }

    public T getValor() {
        return valor;
    }

    public List<Aresta<T>> getAdjacentes() {
        return adjacentes;
    }

    public void adicionarAdjacente(Vertice<T> adjacente, double peso) {
        Aresta<T> aresta = new Aresta<>(this, adjacente, peso);
        adjacentes.add(aresta);
        Collections.sort(adjacentes, Comparator.comparingDouble(Aresta::getPeso));
    }

    public void removerAdjacente(Vertice<T> adjacente) {
        adjacentes.removeIf(a -> a.getVertice2().equals(adjacente));
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getTempoPartida() {
        return tempoPartida;
    }

    public void setTempoPartida(int tempoPartida) {
        this.tempoPartida = tempoPartida;
    }

    @Override
    public String toString() {
        return valor.toString();
    }

    public int compareTo(Vertice<T> outroVertice) {
        // Usa o método compareTo do tipo T para comparar os valores dos vértices
        return this.valor.compareTo(outroVertice.getValor());
    }
}
