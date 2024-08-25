package Grafo.src.Q3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<T> valor;
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta<T>> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<T>>();
        this.arestas = new ArrayList<Aresta<T>>();
    }

    public void adicionarVertice(T dado) {
        Vertice<T> novoVertice = new Vertice<T>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(Double peso, T dadoInicio, T dadoFim) {
        Vertice<T> inicio = this.getVertice(dadoInicio);
        Vertice<T> fim = this.getVertice(dadoFim);
        Aresta<T> aresta = new Aresta<T>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public void adicionarAresta(T dadoInicio, T dadoFim) {
        Vertice<T> inicio = this.getVertice(dadoInicio);
        Vertice<T> fim = this.getVertice(dadoFim);
        Aresta<T> aresta = new Aresta<T>(inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<T> getVertice(T dado) {
        Vertice<T> vertice = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado().equals(dado)) {
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }
    public boolean contemVertice(T dado) {
        return this.getVertice(dado) != null;
    }

    public void buscaEmLargura() {
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();
        Vertice<T> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while (fila.size() > 0) {
            Vertice<T> visitado = fila.get(0);
            for (int i = 0; i < visitado.getArestasSaida().size(); i++) {
                Vertice<T> proximo = visitado.getArestasSaida().get(i).getFim();
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
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        for (Vertice<T> vertice : this.vertices) {
            if (!marcados.contains(vertice)) {
                dfsRecursivo(vertice, marcados);
            }
        }
    }

    public boolean verificaVerticeRaiz() {
        if (this.vertices.isEmpty()) return false;

        for (Vertice<T> vertice : this.vertices) {
            if (isVerticeRaizDFS(vertice)) {
                System.out.println("O VR é: " + vertice.getDado());
                return true;
            }
        }
        System.out.println("O grafo não possui VR");
        return false;
    }

    private boolean isVerticeRaizDFS(Vertice<T> vertice) {
        ArrayList<Vertice<T>> visitados = new ArrayList<Vertice<T>>();
        dfsRecursivo(vertice, visitados);
        return visitados.size() == this.vertices.size();
    }
    private void dfsRecursivo(Vertice<T> vertice, ArrayList<Vertice<T>> marcados) {
        marcados.add(vertice);
        //System.out.println(vertice.getDado());
        for (Aresta<T> aresta : vertice.getArestasSaida()) {
            Vertice<T> proximo = aresta.getFim();
            if (!marcados.contains(proximo)) {
                dfsRecursivo(proximo, marcados);
            }
        }
    }

    public void mostrarLigacoesVertice(T dado) {
        Vertice<T> vertice = this.getVertice(dado);
        if (vertice != null) {
            int ligacoes = vertice.contarLigacoes();
            System.out.println("O vértice " + dado + " tem " + ligacoes + " ligações.");
        } else {
            System.out.println("Vértice não encontrado.");
        }
    }

    public void carregarDeArquivo(String nomeArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        String linha;

        while ((linha = br.readLine()) != null) {
            // Remove espaços em branco ao redor da linha
            linha = linha.trim();

            // Verifica se a linha não está vazia
            if (!linha.isEmpty()) {
                // Verifica se a linha contém uma aresta (contém ';')
                if (linha.contains(";")) {
                    String[] vertices = linha.split(";");

                    // Remover espaços em branco dos vértices
                    T valorInicio = (T) vertices[0].trim();
                    T valorFim = (T) vertices[1].trim();

                    // Verifica se os vértices não são vazios
                    if (!valorInicio.equals("") && !valorFim.equals("")) {
                        if (!contemVertice(valorInicio)) {
                            adicionarVertice(valorInicio);
                        }
                        if (!contemVertice(valorFim)) {
                            adicionarVertice(valorFim);
                        }
                        adicionarAresta(valorInicio, valorFim);
                    }
                } else {
                    // Caso contrário, trata como um vértice isolado
                    T valorVertice = (T) linha.trim();
                    if (!contemVertice(valorVertice)) {
                        adicionarVertice(valorVertice);
                    }
                }
            }
        }

        br.close();
    }

}