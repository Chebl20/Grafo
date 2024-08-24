package Grafo.src.Q6.grafo;

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

    public List<Aresta<T>> chevi() {
        List<Aresta<T>> arestas = new ArrayList<>();
        List<Aresta<T>> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(vertices.size());

        // Adicionar as arestas não duplicadas
        for (Vertice<T> vertice : vertices) {
            for (Aresta<T> aresta : vertice.getAdjacentes()) {
                if (aresta.getVertice1().compareTo(aresta.getVertice2()) < 0) {
                    arestas.add(aresta);
                }
            }
        }

        // Ordenar as arestas pelo peso
        bubbleSort(arestas);

        // Construir a MST
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

    public List<Vertice<T>> geraCiclo(Vertice<T> origem) {
        // Primeiro, encontre a MST
        List<Aresta<T>> mst = chevi();

        // Crie um novo grafo baseado na MST
        Grafo<T> grafoMST = new Grafo<>();
        for (Vertice<T> vertice : vertices) {
            grafoMST.inserirVertice(vertice.getValor());
        }
        for (Aresta<T> aresta : mst) {
            grafoMST.inserirAresta(aresta.getVertice1().getValor(), aresta.getVertice2().getValor(), aresta.getPeso());
        }

        // Utilize o grafo da MST para tentar encontrar um ciclo Hamiltoniano
        List<Vertice<T>> ciclo = new ArrayList<>();
        Set<Vertice<T>> visitados = new HashSet<>();
        boolean encontrouCiclo = grafoMST.dfsCiclo(origem, origem, ciclo, visitados, 0);

        if (encontrouCiclo) {
            ciclo.add(origem); // Feche o ciclo conectando ao vértice de origem
            return ciclo;
        } else {
            System.out.println("Não foi possível encontrar uma possível rota.");
            return Collections.emptyList();
        }
    }

    private boolean dfsCiclo(Vertice<T> atual, Vertice<T> origem, List<Vertice<T>> ciclo,
                             Set<Vertice<T>> visitados, int nivel) {
        ciclo.add(atual);
        visitados.add(atual);

        if (nivel == vertices.size() - 1) {
            if (origem.getAdjacentes().stream().anyMatch(a -> a.getVertice2().equals(atual))) {
                return true;
            } else {
                ciclo.remove(ciclo.size() - 1);
                visitados.remove(atual);
                return false;
            }
        }

        for (Aresta<T> aresta : atual.getAdjacentes()) {
            Vertice<T> adjacente = aresta.getVertice2();
            if (!visitados.contains(adjacente)) {
                if (dfsCiclo(adjacente, origem, ciclo, visitados, nivel + 1)) {
                    return true;
                }
            }
        }

        ciclo.remove(ciclo.size() - 1);
        visitados.remove(atual);
        return false;
    }

    public void bubbleSort(List<Aresta<T>> arestas) {
        int n = arestas.size();
        boolean trocou;

        do {
            trocou = false;
            for (int i = 0; i < n - 1; i++) {
                Aresta<T> a1 = arestas.get(i);
                Aresta<T> a2 = arestas.get(i + 1);

                // Comparar os pesos em ordem decrescente
                if (a1.getPeso() < a2.getPeso()) {
                    // Trocar se o peso da aresta atual for menor que o da próxima
                    Collections.swap(arestas, i, i + 1);
                    trocou = true;
                }
            }
            n--; // Reduz o tamanho da parte não ordenada
        } while (trocou); // Continua até que nenhuma troca seja feita
    }
    public void imprimirCiclo(List<Vertice<T>> ciclo) {
        for (int i = 0; i < ciclo.size(); i++) {
            Vertice<T> atual = ciclo.get(i);
            Vertice<T> proximo = ciclo.get((i + 1) % ciclo.size());
            if (atual != proximo) {
                System.out.println(atual + " -> " + proximo);
            }
        }
    }

    private int pegarIndice(Vertice<T> v) {
        return vertices.indexOf(v);
    }

}