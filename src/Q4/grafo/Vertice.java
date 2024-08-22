package Grafo.src.Q4.grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Vertice<T extends Comparable<T>> {
    private T valor;
    private List<Vertice<T>> adjacentes;
    private int tempoChegada;
    private int tempoPartida;

    public Vertice(T valor) {
        this.valor = valor;
        this.adjacentes = new ArrayList<>();
        this.tempoChegada = -1;  // Inicialmente -1, indicando que ainda não foi visitado
        this.tempoPartida = -1;  // Inicialmente -1, indicando que ainda não foi visitado
    }

    public T getValor() {
        return valor;
    }

    public List<Vertice<T>> getAdjacentes() {
        return adjacentes;
    }

    public void adicionarAdjacente(Vertice<T> adjacente) {
        adjacentes.add(adjacente);
        Collections.sort(adjacentes, new VerticeComparator<T>());
    }

    public class VerticeComparator<T extends Comparable<T>> implements Comparator<Vertice<T>> {
        @Override
        public int compare(Vertice<T> v1, Vertice<T> v2) {
            return v1.getValor().compareTo(v2.getValor());
        }
    }

    public void removerAdjacente(Vertice<T> adjacente) {
        adjacentes.remove(adjacente);
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
}
