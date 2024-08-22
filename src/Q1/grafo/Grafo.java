package Grafo.src.Q1.grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Grafo<T extends Comparable<T>> {
    private List<Vertice<T>> vertices;
    private int tempo;
    private Set<Vertice<T>> visitados;
    private Set<Vertice<T>> recStack; // Pilha de recursão para detecção de ciclos

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.visitados = new HashSet<>();
        this.recStack = new HashSet<>();
        this.tempo = 0;
    }

    public void inserirVertice(T dado) {
        Vertice<T> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }

    public void inserirAresta(T dadoInicio, T dadoFim) {
        Vertice<T> inicio = this.pegarVertice(dadoInicio);
        Vertice<T> fim = this.pegarVertice(dadoFim);

        if (inicio != null && fim != null) {
            inicio.adicionarAdjacente(fim);
        }
    }

    public Vertice<T> pegarVertice(T dado) {
        for (Vertice<T> vertice : this.vertices) {
            if (vertice.getValor().equals(dado)) {
                return vertice;
            }
        }
        return null;
    }

    public void imprimirGrafo() {
        for (Vertice<T> vertice : vertices) {
            System.out.print(vertice.getValor() + ": ");
            for (Vertice<T> adj : vertice.getAdjacentes()) {
                System.out.print(adj.getValor() + " ");
            }
            System.out.println();
        }
    }

    public boolean contemVertice(T dado) {
        return this.pegarVertice(dado) != null;
    }

    public boolean contemAresta(T dadoInicio, T dadoFim) {
        Vertice<T> inicio = this.pegarVertice(dadoInicio);
        Vertice<T> fim = this.pegarVertice(dadoFim);

        if (inicio != null && fim != null) {
            return inicio.getAdjacentes().contains(fim);
        }
        return false;
    }

    public void removerAresta(T dadoInicio, T dadoFim) {
        Vertice<T> inicio = this.pegarVertice(dadoInicio);
        Vertice<T> fim = this.pegarVertice(dadoFim);

        if (inicio != null && fim != null) {
            inicio.getAdjacentes().remove(fim);
        }
    }

    public void removerVertice(T dado) {
        Vertice<T> verticeParaRemover = this.pegarVertice(dado);
        if (verticeParaRemover != null) {
            // Remover todas as arestas que apontam para este vértice
            for (Vertice<T> vertice : this.vertices) {
                vertice.getAdjacentes().remove(verticeParaRemover);
            }
            // Remover o próprio vértice
            this.vertices.remove(verticeParaRemover);
        }
    }
}
