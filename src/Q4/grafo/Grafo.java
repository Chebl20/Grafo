package Grafo.src.Q4.grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.*;

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
    public boolean contemVertice(T dado) {
        return this.pegarVertice(dado) != null;
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

    public boolean ehBipartido() {
        Map<Vertice<T>, Integer> cores = new HashMap<>();

        for (Vertice<T> vertice : vertices) {
            if (!cores.containsKey(vertice)) {
                if (!dfsBipartido(vertice, cores, 0)) {
                    System.out.println("O grafo NÃO é bipartido.");
                    return false;
                }
            }
        }

        // Se o grafo é bipartido, separamos os vértices nas duas partições
        List<Vertice<T>> particao1 = new ArrayList<>();
        List<Vertice<T>> particao2 = new ArrayList<>();

        for (Map.Entry<Vertice<T>, Integer> entry : cores.entrySet()) {
            if (entry.getValue() == 0) {
                particao1.add(entry.getKey());
            } else {
                particao2.add(entry.getKey());
            }
        }

        System.out.println("O grafo É bipartido: Partição 1 " + particao1 + " e Partição 2 " + particao2);
        return true;
    }

    private boolean dfsBipartido(Vertice<T> vertice, Map<Vertice<T>, Integer> cores, int cor) {
        cores.put(vertice, cor);

        for (Vertice<T> vizinho : vertice.getAdjacentes()) {
            if (!cores.containsKey(vizinho)) {
                if (!dfsBipartido(vizinho, cores, 1 - cor)) {
                    return false;
                }
            } else if (cores.get(vizinho) == cores.get(vertice)) {
                return false;
            }
        }
        return true;
    }

    public void carregarDeArquivo(String nomeArquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim(); // Remove espaços em branco ao redor da linha

                // Verifica se a linha não está vazia
                if (!linha.isEmpty()) {
                    // Verifica se a linha contém uma aresta (contém ';')
                    if (linha.contains(";")) {
                        String[] partes = linha.split(";");

                        // Verifica se há exatamente 2 partes (vértice início e vértice fim)
                        if (partes.length == 2) {
                            T valorInicio = (T) partes[0].trim();
                            T valorFim = (T) partes[1].trim();

                            // Verifica se os vértices não são vazios
                            if (!valorInicio.equals("") && !valorFim.equals("")) {
                                adicionarVerticeSeNaoExistir(valorInicio);
                                adicionarVerticeSeNaoExistir(valorFim);

                                // Adiciona a aresta entre os vértices
                                inserirAresta(valorInicio, valorFim);
                            } else {
                                System.err.println("Vértices inválidos na linha: " + linha);
                            }
                        } else {
                            System.err.println("Formato de linha inválido: " + linha);
                        }
                    } else {
                        // Caso contrário, trata como um vértice isolado
                        T valorVertice = (T) linha.trim();
                        if (!valorVertice.equals("")) {
                            adicionarVerticeSeNaoExistir(valorVertice);
                        } else {
                            System.err.println("Vértice inválido na linha: " + linha);
                        }
                    }
                }
            }
        }
    }

    private void adicionarVerticeSeNaoExistir(T vertice) {
        if (!contemVertice(vertice)) {
            inserirVertice(vertice);
        }
    }


}
