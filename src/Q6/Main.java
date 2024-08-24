package Grafo.src.Q6;

import Grafo.src.Q6.grafo.Aresta;
import Grafo.src.Q6.grafo.Grafo;
import Grafo.src.Q6.grafo.Vertice;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criação do grafo
        Grafo<String> grafo = new Grafo<>();

        // Inserir vértices
        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");
        grafo.inserirVertice("F");
        grafo.inserirVertice("G");

        // Inserir arestas
        grafo.inserirAresta("A", "B", 7.0);
        grafo.inserirAresta("B", "C", 8.0);
        grafo.inserirAresta("C", "D", 5.0);
        grafo.inserirAresta("D", "E", 9.0);
        grafo.inserirAresta("E", "F", 11.0);
        grafo.inserirAresta("F", "G", 6.0);
        grafo.inserirAresta("A", "G", 5.0);

        grafo.inserirAresta("B", "D", 7.0);

        grafo.inserirAresta("F", "D", 8.0);

        grafo.inserirAresta("G", "D", 15.0);

        grafo.inserirAresta("B", "G", 9.0);

        // Calcular a MST usando Kruskal
        List<Aresta<String>> mst = grafo.chevi();
        System.out.println("Árvore Geradora Mínima:");
        for (Aresta<String> aresta : mst) {
            System.out.println(aresta.getVertice1().getValor() + " -(" + aresta.getPeso() + ")-> " + aresta.getVertice2().getValor());
        }

        // Gerar um ciclo a partir da MST
        Vertice<String> verticeOrigem = grafo.pegarVertice("A");
        List<Vertice<String>> ciclo = grafo.geraCiclo(verticeOrigem);
        System.out.println("\nRota calculada :");
        grafo.imprimirCiclo(ciclo);
//        System.out.println("\nRota calculada 2  :");
//
//        for (Vertice<String> vertice : ciclo) {
//            System.out.println(vertice.getValor());
//        }
    }
}
