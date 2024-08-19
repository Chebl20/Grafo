package Q2;

import Q2.grafo.Grafo;

public class Main {
    public static void main(String[] args) {
        // Criação do grafo com vértices do tipo Integer
        Grafo<Integer> meuGrafo = new Grafo<>();

        // Inserindo vértices
        meuGrafo.inserirVertice(0);
        meuGrafo.inserirVertice(1);
        meuGrafo.inserirVertice(2);
        meuGrafo.inserirVertice(3);
        meuGrafo.inserirVertice(4);
        meuGrafo.inserirVertice(5);
        meuGrafo.inserirVertice(6);
        meuGrafo.inserirVertice(7);

        // Inserindo arestas
        meuGrafo.inserirAresta(1.0,0, 1);
        meuGrafo.inserirAresta(1.0,0, 2);
        meuGrafo.inserirAresta(1.0,2, 3);
        meuGrafo.inserirAresta(1.0,2, 4);
        meuGrafo.inserirAresta(1.0,3, 1);
        meuGrafo.inserirAresta(1.0,3, 5);
        meuGrafo.inserirAresta(1.0,4, 5);
        meuGrafo.inserirAresta(1.0,6, 7);

        // Imprimindo o grafo
        System.out.println("Grafo:");
        meuGrafo.imprimirGrafo();

        // Imprimindo os tempos de chegada e partida
        System.out.println("\nTempos de Chegada e Partida dos Vértices:");
        meuGrafo.imprimirTemposDFS();
    }
}
//
//0 (Chegada: 0, Partida: 11)
//        1 (Chegada: 1, Partida: 2)
//        2 (Chegada: 3, Partida: 10)
//        3 (Chegada: 4, Partida: 7)
//        4 (Chegada: 8, Partida: 9)
//        5 (Chegada: 5, Partida: 6)
//        6 (Chegada: 12, Partida: 15)
//        7 (Chegada: 13, Partida: 14)
