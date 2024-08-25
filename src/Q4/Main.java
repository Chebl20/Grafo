package Grafo.src.Q4;


import Grafo.src.Q4.grafo.Grafo;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>();
//
//        try {
//            System.out.println("Grafo por arquivo:");
//            grafo.carregarDeArquivo("src/Grafo/src/Q4/arquivo3.txt");
//            grafo.ehBipartido();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //Caso sem ser Bipartido
//        // Inserindo os vértices
//        grafo.inserirVertice("A");
//        grafo.inserirVertice("B");
//        grafo.inserirVertice("C");
//        grafo.inserirVertice("D");
//        grafo.inserirVertice("E");
//        grafo.inserirVertice("F");
//        grafo.inserirVertice("G");
//        grafo.inserirVertice("H");
//        grafo.inserirVertice("I");
//
//        // Inserindo as arestas
//        grafo.inserirAresta("A", "B");
//        grafo.inserirAresta("A", "C");
//        grafo.inserirAresta("A", "F");
//        grafo.inserirAresta("B", "G");
//        grafo.inserirAresta("B", "H");
//        grafo.inserirAresta("C", "I");
//        grafo.inserirAresta("C", "D");
//        grafo.inserirAresta("D", "I");
//        grafo.inserirAresta("E", "F");
//        grafo.inserirAresta("E", "G");
////////////////////////////////////////////////////////////////////////////////

//        caso 2 bipartido
        // Inserindo os vértices
        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");
        grafo.inserirVertice("F");
        grafo.inserirVertice("G");
        grafo.inserirVertice("H");
        grafo.inserirVertice("I");

        // Inserindo as arestas
        grafo.inserirAresta("A", "B");
        grafo.inserirAresta("A", "D");
        grafo.inserirAresta("B", "C");
        grafo.inserirAresta("B", "G");
        grafo.inserirAresta("C", "F");
        grafo.inserirAresta("D", "E");
        grafo.inserirAresta("E", "H");
        grafo.inserirAresta("E", "F");
        grafo.inserirAresta("F", "I");
        grafo.inserirAresta("G", "H");
        grafo.inserirAresta("H", "I");

        // Teste de bipartição
        grafo.ehBipartido();
    }
}
