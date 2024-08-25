package Grafo.src.Q1;


import Grafo.src.Q1.grafo.Grafo;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Criação do grafo com vértices do tipo Integer
        Grafo<String> meuGrafo = new Grafo<>();

        // Inserindo vértices
//        meuGrafo.inserirVertice(1);
//        meuGrafo.inserirVertice(2);
//        meuGrafo.inserirVertice(3);
//
//        // Inserindo arestas
//        meuGrafo.inserirAresta(1, 2);
//        meuGrafo.inserirAresta(2, 3);
//        meuGrafo.inserirAresta(1, 3);

        try {
            meuGrafo.carregarDeArquivo("src/Grafo/src/Q1/arquivo.txt");
            meuGrafo.removerAresta("VerticeA", "VerticeC");
            meuGrafo.imprimirGrafo();
            meuGrafo.salvarEmArquivo("src/Grafo/src/Q1/saida.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        // Imprimindo o grafo após inserções
//        System.out.println("Grafo após inserção de vértices e arestas:");
//        meuGrafo.imprimirGrafo();
//
//        // Verificando a presença de um vértice e uma aresta
//        int verticeParaBuscar = 2;
//        System.out.println("Vértice " + verticeParaBuscar + " está presente? " + meuGrafo.contemVertice(verticeParaBuscar));
//        System.out.println("Aresta entre 1 e 2 existe? " + meuGrafo.contemAresta(1, 2));
//
//        // Removendo uma aresta
//        meuGrafo.removerAresta(1, 2);
//        System.out.println("\nGrafo após remover a aresta entre 1 e 2:");
//        meuGrafo.imprimirGrafo();
//        System.out.println("Aresta entre 1 e 2 existe? " + meuGrafo.contemAresta(1, 2));
//
//        // Removendo um vértice
//        meuGrafo.removerVertice(3);
//        System.out.println("\nGrafo após remover o vértice 3:");
//        meuGrafo.imprimirGrafo();
//        System.out.println("Vértice 3 está presente? " + meuGrafo.contemVertice(3));
    }
}
