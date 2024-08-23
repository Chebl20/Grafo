package Grafo.src.Q3;

public class Main {
    public static void main (String[] args){
        Grafo<Integer> grafo = new Grafo<>();

        grafo.adicionarVertice(0);
        grafo.adicionarVertice(1);
        grafo.adicionarVertice(2);
        grafo.adicionarVertice(3);
        grafo.adicionarVertice(4);
        grafo.adicionarVertice(5);


        grafo.adicionarAresta(5, 0);
        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(1, 2);
        grafo.adicionarAresta(2, 3);
        grafo.adicionarAresta(3, 0);
        grafo.adicionarAresta(4, 3);
        grafo.adicionarAresta(4, 5);

        grafo.verificaVerticeRaiz();
        // grafo.buscaEmProfundidade();

    }
}
