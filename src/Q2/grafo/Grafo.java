package Grafo.src.Q2.grafo;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
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

    public void inserirAresta( T dadoInicio, T dadoFim) {
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

    public void imprimirTemposDFS() {
        visitados.clear();
        tempo = 0;

        for (Vertice<T> vertice : vertices) {
            if (!visitados.contains(vertice)) {
                buscaEmProfundidadeVisitante(vertice);
            }
        }

        for (Vertice<T> vertice : vertices) {
            System.out.println(vertice.getValor() + " (Chegada: " + vertice.getTempoChegada() + ", Partida: " + vertice.getTempoPartida() + ")");
        }
    }

    private void buscaEmProfundidadeVisitante(Vertice<T> vertice) {
        vertice.setTempoChegada(tempo++);  // Atualiza o tempo de chegada
        visitados.add(vertice);
        recStack.add(vertice);

        for (Vertice<T> vizinho : vertice.getAdjacentes()) {
            if (!visitados.contains(vizinho)) {
                buscaEmProfundidadeVisitante(vizinho);
            }
        }

        vertice.setTempoPartida(tempo++);  // Atualiza o tempo de partida
        recStack.remove(vertice);
    }

}
