package Grafo.src.Q5.grafo;

import Grafo.src.Q5.Estruturas.UnionFind;

import java.util.*;

public class Grafo<T extends Comparable<T>> {
    private List<Vertice<T>> vertices;
    private Set<Vertice<T>> visitados;
    private Set<Vertice<T>> recStack;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.visitados = new HashSet<>();
        this.recStack = new HashSet<>();
    }

    public void inserirVertice(T dado) {
        Vertice<T> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }

    public void inserirAresta(T dadoInicio, T dadoFim, double peso) {
        Vertice<T> inicio = this.pegarVertice(dadoInicio);
        Vertice<T> fim = this.pegarVertice(dadoFim);

        if (inicio != null && fim != null) {
            inicio.adicionarAdjacente(fim, peso);
            fim.adicionarAdjacente(inicio, peso);
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
            for (Aresta<T> aresta : vertice.getAdjacentes()) {
                System.out.print(aresta.getVertice2().getValor() + "(" + aresta.getPeso() + ") ");
            }
            System.out.println();
        }
    }

    public List<Aresta<T>> boruvka() {
        UnionFind uf = new UnionFind(vertices.size());
        List<Aresta<T>> mst = new ArrayList<>();
        int componentes = vertices.size();

        while (componentes > 1) {
            Aresta<T>[] menorAresta = new Aresta[vertices.size()];

            for (Vertice<T> vertice : vertices) {
                for (Aresta<T> aresta : vertice.getAdjacentes()) {
                    int set1 = uf.find(pegarIndice(aresta.getVertice1()));
                    int set2 = uf.find(pegarIndice(aresta.getVertice2()));

                    if (set1 != set2) {
                        if (menorAresta[set1] == null || aresta.getPeso() < menorAresta[set1].getPeso()) {
                            menorAresta[set1] = aresta;
                        }
                        if (menorAresta[set2] == null || aresta.getPeso() < menorAresta[set2].getPeso()) {
                            menorAresta[set2] = aresta;
                        }
                    }
                }
            }

            for (Aresta<T> aresta : menorAresta) {
                if (aresta != null) {
                    int set1 = uf.find(pegarIndice(aresta.getVertice1()));
                    int set2 = uf.find(pegarIndice(aresta.getVertice2()));

                    if (set1 != set2) {
                        mst.add(aresta);
                        uf.union(set1, set2);
                        componentes--;
                    }
                }
            }
        }

        return mst;
    }

    public List<Aresta<T>> kruskal() {
        List<Aresta<T>> arestas = new ArrayList<>();
        List<Aresta<T>> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(vertices.size());

        for (Vertice<T> vertice : vertices) {
            for (Aresta<T> aresta : vertice.getAdjacentes()) {
                // Adicionar a aresta apenas se não for duplicada
                if (aresta.getVertice1().compareTo(aresta.getVertice2()) < 0) {
                    arestas.add(aresta);
                }
            }
        }

        Collections.sort(arestas, Comparator.comparingDouble(Aresta::getPeso));

        for (Aresta<T> aresta : arestas) {
            Vertice<T> v1 = aresta.getVertice1();
            Vertice<T> v2 = aresta.getVertice2();

            int root1 = uf.find(pegarIndice(v1));
            int root2 = uf.find(pegarIndice(v2));

            if (root1 != root2) {
                mst.add(aresta);
                uf.union(root1, root2);
            }
        }

        return mst;
    }

    private int pegarIndice(Vertice<T> v) {
        return vertices.indexOf(v);
    }

    public List<Aresta<T>> prim(T valorInicial) {
        Vertice<T> verticeInicial = pegarVertice(valorInicial);
        if (verticeInicial == null) {
            throw new IllegalArgumentException("Vértice inicial não encontrado");
        }

        List<Aresta<T>> mst = new ArrayList<>();
        Set<Vertice<T>> visitados = new HashSet<>();
        PriorityQueue<Aresta<T>> pq = new PriorityQueue<>(Comparator.comparingDouble(Aresta::getPeso));

        visitados.add(verticeInicial);

        for (Aresta<T> aresta : verticeInicial.getAdjacentes()) {
            pq.add(aresta);
        }

        while (!pq.isEmpty()) {
            Aresta<T> aresta = pq.poll();
            Vertice<T> vertice1 = aresta.getVertice1();
            Vertice<T> vertice2 = aresta.getVertice2();

            if (visitados.contains(vertice2)) {
                continue;
            }

            visitados.add(vertice2);
            mst.add(aresta);

            for (Aresta<T> adjacente : vertice2.getAdjacentes()) {
                if (!visitados.contains(adjacente.getVertice2())) {
                    pq.add(adjacente);
                }
            }
        }

        return mst;
    }
}