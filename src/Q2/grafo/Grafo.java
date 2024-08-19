package Q2.grafo;

import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta<T>> arestas;
    private int tempo;
    private ArrayList<Vertice<T>> visitados;
    private ArrayList<Integer> temposDeChegada;
    private ArrayList<Integer> temposDePartida;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.visitados = new ArrayList<>();
        this.temposDeChegada = new ArrayList<>();
        this.temposDePartida = new ArrayList<>();
        this.tempo = 0;
    }

    public void inserirVertice(T dado) {
        Vertice<T> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }

    public void inserirAresta(Double peso, T dadoInicio, T dadoFim) {
        Vertice<T> inicio = this.pegarVertice(dadoInicio);
        Vertice<T> fim = this.pegarVertice(dadoFim);

        if (inicio != null && fim != null) {
            Aresta<T> aresta = new Aresta<>(inicio, fim);
            inicio.adicionarAdjacente(fim);
            fim.adicionarAdjacente(inicio); // Se o grafo é não direcionado
            this.arestas.add(aresta);
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
        temposDeChegada.clear();
        temposDePartida.clear();
        tempo = 0;

        for (Vertice<T> vertice : vertices) {
            if (!visitados.contains(vertice)) {
                buscaEmProfundidadeVisitante(vertice);
            }
        }

        for (Vertice<T> vertice : vertices) {
            int index = vertices.indexOf(vertice);
            System.out.println(vertice.getValor() + " (Chegada: " + vertice.getTempoChegada() + ", Partida: " + vertice.getTempoPartida() + ")");
        }
    }

    private void buscaEmProfundidadeVisitante(Vertice<T> vertice) {
        vertice.setTempoChegada(tempo++); // Define o tempo de chegada e incrementa o tempo
        visitados.add(vertice);
        int index = vertices.indexOf(vertice);
        temposDeChegada.add(vertice.getTempoChegada());

        for (Vertice<T> vizinho : vertice.getAdjacentes()) {
            if (!visitados.contains(vizinho)) {
                buscaEmProfundidadeVisitante(vizinho);
            }
        }

        vertice.setTempoPartida(tempo++); // Define o tempo de partida e incrementa o tempo
        temposDePartida.add(vertice.getTempoPartida());
    }
}
