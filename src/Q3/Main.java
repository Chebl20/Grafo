package Grafo.src.Q3;

import java.io.IOException;

public class Main {
    public static void main (String[] args){
        Grafo<Integer> meuGrafo = new Grafo<>();

        try {
            System.out.println("Grafo por arquivo:");
            meuGrafo.carregarDeArquivo("src/Grafo/src/Q3/arquivo2.txt");
            meuGrafo.verificaVerticeRaiz();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        meuGrafo.adicionarVertice(0);
//        meuGrafo.adicionarVertice(1);
//        meuGrafo.adicionarVertice(2);
//        meuGrafo.adicionarVertice(3);
//        meuGrafo.adicionarVertice(4);
//        meuGrafo.adicionarVertice(5);
//
//
//        meuGrafo.adicionarAresta(5, 0);
//        meuGrafo.adicionarAresta(0, 1);
//        meuGrafo.adicionarAresta(1, 2);
//        meuGrafo.adicionarAresta(2, 3);
//        meuGrafo.adicionarAresta(3, 0);
//        meuGrafo.adicionarAresta(4, 3);
//        meuGrafo.adicionarAresta(4, 5);
//
//        meuGrafo.verificaVerticeRaiz();
//        // meuGrafo.buscaEmProfundidade();

    }
}
