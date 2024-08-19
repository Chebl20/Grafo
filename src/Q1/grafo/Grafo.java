package Q1.grafo;

import java.util.*;

public class Grafo<T> {
    private Map<T, Set<T>> vertices;

    public Grafo() {
        vertices = new HashMap<>();
    }

    public void inserirVertice(T vertice) {
        vertices.putIfAbsent(vertice, new HashSet<>());
    }

    public void removerVertice(T vertice) {
        if (vertices.containsKey(vertice)) {
            vertices.remove(vertice);
            for (T v : vertices.keySet()) {
                vertices.get(v).remove(vertice);
            }
        }
    }

    public void inserirAresta(T vertice1, T vertice2) {
        if (vertices.containsKey(vertice1) && vertices.containsKey(vertice2)) {
            vertices.get(vertice1).add(vertice2);
            vertices.get(vertice2).add(vertice1);
        }
    }

    public void removerAresta(T vertice1, T vertice2) {
        if (vertices.containsKey(vertice1) && vertices.containsKey(vertice2)) {
            vertices.get(vertice1).remove(vertice2);
            vertices.get(vertice2).remove(vertice1);
        }
    }

    public Set<T> obterAdjacentes(T vertice) {
        return vertices.getOrDefault(vertice, new HashSet<>());
    }

    public boolean contemVertice(T vertice) {
        return vertices.containsKey(vertice);
    }

    public boolean contemAresta(T vertice1, T vertice2) {
        return vertices.containsKey(vertice1) && vertices.get(vertice1).contains(vertice2);
    }

    public void imprimirGrafo() {
        for (T vertice : vertices.keySet()) {
            Set<T> adjacentes = vertices.get(vertice);
            System.out.print(vertice + ": ");
            for (T adj : adjacentes) {
                System.out.print(adj + " ");
            }
            System.out.println();
        }
    }
}
