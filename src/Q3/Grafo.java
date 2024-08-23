package Grafo.src.Q3;

import java.util.ArrayList;

public class Grafo<Interger> {
    private ArrayList<Interger> valor;
    private ArrayList<Vertice<Interger>> vertices;
    private ArrayList<Aresta<Interger>> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<Interger>>();
        this.arestas = new ArrayList<Aresta<Interger>>();
    }

    public void adicionarVertice(Interger dado) {
        Vertice<Interger> novoVertice = new Vertice<Interger>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(Double peso, Interger dadoInicio, Interger dadoFim) {
        Vertice<Interger> inicio = this.getVertice(dadoInicio);
        Vertice<Interger> fim = this.getVertice(dadoFim);
        Aresta<Interger> aresta = new Aresta<Interger>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public void adicionarAresta(Interger dadoInicio, Interger dadoFim) {
        Vertice<Interger> inicio = this.getVertice(dadoInicio);
        Vertice<Interger> fim = this.getVertice(dadoFim);
        Aresta<Interger> aresta = new Aresta<Interger>(inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<Interger> getVertice(Interger dado) {
        Vertice<Interger> vertice = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado().equals(dado)) {
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

    public void buscaEmLargura() {
        ArrayList<Vertice<Interger>> marcados = new ArrayList<Vertice<Interger>>();
        ArrayList<Vertice<Interger>> fila = new ArrayList<Vertice<Interger>>();
        Vertice<Interger> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while (fila.size() > 0) {
            Vertice<Interger> visitado = fila.get(0);
            for (int i = 0; i < visitado.getArestasSaida().size(); i++) {
                Vertice<Interger> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.contains(proximo)) {
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }

    public void buscaEmProfundidade() {
        ArrayList<Vertice<Interger>> marcados = new ArrayList<Vertice<Interger>>();
        for (Vertice<Interger> vertice : this.vertices) {
            if (!marcados.contains(vertice)) {
                dfsRecursivo(vertice, marcados);
            }
        }
    }

    public boolean verificaVerticeRaiz() {
        if (this.vertices.isEmpty()) return false;

        for (Vertice<Interger> vertice : this.vertices) {
            if (isVerticeRaizDFS(vertice)) {
                System.out.println("O VR é: " + vertice.getDado());
                return true;
            }
        }
        System.out.println("O grafo não possui VR");
        return false;
    }

    private boolean isVerticeRaizDFS(Vertice<Interger> vertice) {
        ArrayList<Vertice<Interger>> visitados = new ArrayList<Vertice<Interger>>();
        dfsRecursivo(vertice, visitados);
        return visitados.size() == this.vertices.size();
    }
    private void dfsRecursivo(Vertice<Interger> vertice, ArrayList<Vertice<Interger>> marcados) {
        marcados.add(vertice);
        System.out.println(vertice.getDado());
        for (Aresta<Interger> aresta : vertice.getArestasSaida()) {
            Vertice<Interger> proximo = aresta.getFim();
            if (!marcados.contains(proximo)) {
                dfsRecursivo(proximo, marcados);
            }
        }
    }

    public void mostrarLigacoesVertice(Interger dado) {
        Vertice<Interger> vertice = this.getVertice(dado);
        if (vertice != null) {
            int ligacoes = vertice.contarLigacoes();
            System.out.println("O vértice " + dado + " tem " + ligacoes + " ligações.");
        } else {
            System.out.println("Vértice não encontrado.");
        }
    }
}
