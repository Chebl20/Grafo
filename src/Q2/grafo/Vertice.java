package Q2.grafo;

import java.util.HashSet;
import java.util.Set;

public class Vertice<T> {
    private T valor;
    private Set<Vertice<T>> adjacentes;
    private int tempoChegada;
    private int tempoPartida;

    public Vertice(T valor) {
        this.valor = valor;
        this.adjacentes = new HashSet<>();
        this.tempoChegada = -1; // Valor padrão indicando que o tempo não foi definido
        this.tempoPartida = -1; // Valor padrão indicando que o tempo não foi definido
    }

    public T getValor() {
        return valor;
    }

    public Set<Vertice<T>> getAdjacentes() {
        return adjacentes;
    }

    public void adicionarAdjacente(Vertice<T> adjacente) {
        adjacentes.add(adjacente);
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
