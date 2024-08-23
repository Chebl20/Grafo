package Grafo.src.Q5;

import Grafo.src.Q5.grafo.Grafo;
import Grafo.src.Q5.grafo.Vertice;
import Grafo.src.Q5.grafo.Aresta;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Cria um grafo com vértices do tipo String
        Grafo<String> grafo = new Grafo<>();

        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");
        grafo.inserirVertice("F");
        grafo.inserirVertice("G");

        // Adiciona arestas com pesos
        grafo.inserirAresta("A", "B", 7.0);
        grafo.inserirAresta("A", "G", 5.0);
        grafo.inserirAresta("B", "C", 8.0);
        grafo.inserirAresta("B", "D", 7.0);
        grafo.inserirAresta("B", "G", 9.0);
        grafo.inserirAresta("C", "D", 5.0);
        grafo.inserirAresta("D", "E", 9.0);
        grafo.inserirAresta("E", "F", 11.0);
        grafo.inserirAresta("F", "D", 8.0);
        grafo.inserirAresta("F", "G", 6.0);
        grafo.inserirAresta("G", "D", 15.0);



        // Imprime o grafo
        System.out.println("Imprimindo grafo:");
        grafo.imprimirGrafo();

        // Testa o algoritmo de Prim
        System.out.println("\nÁrvore Geradora Mínima usando Prim:");
        List<Aresta<String>> primResult = grafo.prim("A");
        for (Aresta<String> aresta : primResult) {
            System.out.println(aresta);
        }

        // Testa o algoritmo de Kruskal
        System.out.println("\nÁrvore Geradora Mínima usando Kruskal:");
        List<Aresta<String>> kruskalResult = grafo.kruskal();
        for (Aresta<String> aresta : kruskalResult) {
            System.out.println(aresta);
        }

        // Testa o algoritmo de Borůvka
        System.out.println("\nÁrvore Geradora Mínima usando Borůvka:");
        List<Aresta<String>> boruvkaResult = grafo.boruvka();
        for (Aresta<String> aresta : boruvkaResult) {
            System.out.println(aresta);
        }
    }
}
