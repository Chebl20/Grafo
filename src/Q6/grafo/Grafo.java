package Grafo.src.Q6.grafo;

import Grafo.src.Q5.Estruturas.UnionFind;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grafo<T extends Comparable<T>> {
    private List<Vertice<T>> vertices;
    private Set<Vertice<T>> visitados;
    private Set<Vertice<T>> recStack;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.visitados = new HashSet<>();
        this.recStack = new HashSet<>();
    }

    public void inserirVertice(T dado) {
        Vertice<T> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }

    public void inserirAresta(T dadoInicio, T dadoFim, double peso) {
        Vertice<T> inicio = this.pegarVertice(dadoInicio);
        Vertice<T> fim = this.pegarVertice(dadoFim);

        if (inicio != null && fim != null) {
            inicio.adicionarAdjacente(fim, peso);
            fim.adicionarAdjacente(inicio, peso);
        }
    }

    public Vertice<T> pegarVertice(T dado) {
        for (Vertice<T> vertice : this.vertices) {
            if (vertice.getValor().equals(dado)) {
                return vertice;
            }
        }
        return null;
    }

    public void imprimirGrafo() {
        for (Vertice<T> vertice : vertices) {
            System.out.print(vertice.getValor() + ": ");
            for (Aresta<T> aresta : vertice.getAdjacentes()) {
                System.out.print(aresta.getVertice2().getValor() + "(" + aresta.getPeso() + ") ");
            }
            System.out.println();
        }
    }

    public List<Aresta<T>> chevi() {
        List<Aresta<T>> mst = new ArrayList<>();
        if (vertices.isEmpty()) return mst;

        Set<Vertice<T>> visitados = new HashSet<>();
        List<Aresta<T>> candidatos = new ArrayList<>();
        Map<Vertice<T>, PriorityQueue<Aresta<T>>> arestasPorVertice = new HashMap<>();
        Vertice<T> inicio = vertices.get(0); // Começar pelo primeiro vértice

        visitados.add(inicio);
        candidatos.addAll(inicio.getAdjacentes());

        for (Aresta<T> aresta : inicio.getAdjacentes()) {
            Vertice<T> adj = aresta.getVertice2();
            arestasPorVertice.computeIfAbsent(adj, k -> new PriorityQueue<>(Comparator.comparingDouble(Aresta<T>::getPeso)))
                    .add(aresta);
        }

        while (visitados.size() < vertices.size()) {
            // Encontre a aresta de menor peso entre os candidatos que conecta um vértice visitado com um não visitado
            Aresta<T> arestaEscolhida = null;
            Vertice<T> verticeEscolhido = null;

            for (Aresta<T> aresta : candidatos) {
                Vertice<T> v1 = aresta.getVertice1();
                Vertice<T> v2 = aresta.getVertice2();
                if (visitados.contains(v1) && !visitados.contains(v2)) {
                    if (arestaEscolhida == null || aresta.getPeso() < arestaEscolhida.getPeso()) {
                        arestaEscolhida = aresta;
                        verticeEscolhido = v2;
                    }
                } else if (visitados.contains(v2) && !visitados.contains(v1)) {
                    if (arestaEscolhida == null || aresta.getPeso() < arestaEscolhida.getPeso()) {
                        arestaEscolhida = aresta;
                        verticeEscolhido = v1;
                    }
                }
            }

            if (arestaEscolhida != null) {
                mst.add(arestaEscolhida);
                visitados.add(verticeEscolhido);
                candidatos.remove(arestaEscolhida);

                // Adicione novas arestas candidatas
                for (Aresta<T> aresta : verticeEscolhido.getAdjacentes()) {
                    Vertice<T> adj = aresta.getVertice2();
                    if (!visitados.contains(adj)) {
                        candidatos.add(aresta);
                        arestasPorVertice.computeIfAbsent(adj, k -> new PriorityQueue<>(Comparator.comparingDouble(Aresta<T>::getPeso)))
                                .add(aresta);
                    }
                }
            } else {
                // Se não houver arestas disponíveis para expansão, o grafo pode estar desconexo
                System.out.println("O grafo pode estar desconexo.");
                break;
            }
        }

        return mst;
    }

    public List<Vertice<T>> geraCiclo(Vertice<T> origem) {
        // Primeiro, encontre a MST
        List<Aresta<T>> mst = chevi();

        // Crie um novo grafo baseado na MST
        Grafo<T> grafoMST = new Grafo<>();
        for (Vertice<T> vertice : vertices) {
            grafoMST.inserirVertice(vertice.getValor());
        }
        for (Aresta<T> aresta : mst) {
            grafoMST.inserirAresta(aresta.getVertice1().getValor(), aresta.getVertice2().getValor(), aresta.getPeso());
        }

        // Utilize o grafo da MST para tentar encontrar um ciclo Hamiltoniano
        List<Vertice<T>> ciclo = new ArrayList<>();
        Set<Vertice<T>> visitados = new HashSet<>();
        boolean encontrouCiclo = grafoMST.dfsCiclo(origem, origem, ciclo, visitados, 0);

        if (encontrouCiclo) {
            ciclo.add(origem); // Feche o ciclo conectando ao vértice de origem
            return ciclo;
        } else {
            System.out.println("Não foi possível encontrar uma possível rota.");
            return Collections.emptyList();
        }
    }

    private boolean dfsCiclo(Vertice<T> atual, Vertice<T> origem, List<Vertice<T>> ciclo,
                             Set<Vertice<T>> visitados, int nivel) {
        ciclo.add(atual);
        visitados.add(atual);

        if (nivel == vertices.size() - 1) {
            if (origem.getAdjacentes().stream().anyMatch(a -> a.getVertice2().equals(atual))) {
                return true;
            } else {
                ciclo.remove(ciclo.size() - 1);
                visitados.remove(atual);
                return false;
            }
        }

        for (Aresta<T> aresta : atual.getAdjacentes()) {
            Vertice<T> adjacente = aresta.getVertice2();
            if (!visitados.contains(adjacente)) {
                if (dfsCiclo(adjacente, origem, ciclo, visitados, nivel + 1)) {
                    return true;
                }
            }
        }

        ciclo.remove(ciclo.size() - 1);
        visitados.remove(atual);
        return false;
    }

    public void bubbleSort(List<Aresta<T>> arestas) {
        int n = arestas.size();
        boolean trocou;

        do {
            trocou = false;
            for (int i = 0; i < n - 1; i++) {
                Aresta<T> a1 = arestas.get(i);
                Aresta<T> a2 = arestas.get(i + 1);

                // Comparar os pesos em ordem decrescente
                if (a1.getPeso() < a2.getPeso()) {
                    // Trocar se o peso da aresta atual for menor que o da próxima
                    Collections.swap(arestas, i, i + 1);
                    trocou = true;
                }
            }
            n--; // Reduz o tamanho da parte não ordenada
        } while (trocou); // Continua até que nenhuma troca seja feita
    }
    public void imprimirCiclo(List<Vertice<T>> ciclo) {
        for (int i = 0; i < ciclo.size(); i++) {
            Vertice<T> atual = ciclo.get(i);
            Vertice<T> proximo = ciclo.get((i + 1) % ciclo.size());
            if (atual != proximo) {
                System.out.println(atual + " -> " + proximo);
            }
        }
    }

    public boolean contemVertice(T dado) {
        return this.pegarVertice(dado) != null;
    }
    public void carregarDoArquivo(String nomeArquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();  // Remove espaços em branco ao redor da linha

                if (!linha.isEmpty()) {
                    if (linha.contains(";")) {
                        String[] partes = linha.split(";");

                        if (partes.length >= 2) {
                            // Verifica se há pelo menos 2 partes e se a linha é válida
                            T valorInicio = (T) partes[0].trim();
                            T valorFim = (T) partes[1].trim();
                            String pesoString = partes.length > 2 ? partes[2].trim() : null;

                            // Verifica se os vértices não são vazios e o peso é válido
                            if (!valorInicio.equals("") && !valorFim.equals("") && (pesoString == null || !pesoString.equals(""))) {
                                double peso;
                                try {
                                    peso = pesoString != null ? Double.parseDouble(pesoString) : 0.0;
                                } catch (NumberFormatException e) {
                                    System.err.println("Peso inválido na linha: " + linha);
                                    continue; // Ignora a linha com peso inválido
                                }

                                if (!contemVertice(valorInicio)) {
                                    inserirVertice(valorInicio);
                                }
                                if (!contemVertice(valorFim)) {
                                    inserirVertice(valorFim);
                                }
                                inserirAresta(valorInicio, valorFim, peso);
                            }
                        } else {
                            System.err.println("Linha inválida: " + linha);
                        }
                    } else {
                        // Caso contrário, trata como um vértice isolado
                        T valorVertice = (T) linha.trim();
                        if (!valorVertice.equals("")) {
                            if (!contemVertice(valorVertice)) {
                                inserirVertice(valorVertice);
                            }
                        }
                    }
                }
            }
        }
    }
    public void carregarDeArquivo(String caminhoArquivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim(); // Remove espaços em branco desnecessários

                // Verifica se a linha não está vazia e contém os delimitadores corretos
                if (!linha.isEmpty() && linha.contains(";")) {
                    String[] partes = linha.split(";");
                    if (partes.length == 3) { // Verifica se há 3 partes (vértice início, vértice fim, peso)
                        T verticeInicio = (T) partes[0].trim();
                        T verticeFim = (T) partes[1].trim();
                        Double peso;

                        try {
                            peso = Double.parseDouble(partes[2].trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Peso inválido na linha: " + linha);
                            continue; // Ignora a linha com peso inválido
                        }

                        // Adiciona os vértices se ainda não existirem
                        adicionarVerticeSeNaoExistir(verticeInicio);
                        adicionarVerticeSeNaoExistir(verticeFim);

                        // Adiciona a aresta entre os vértices
                        inserirAresta(verticeInicio, verticeFim, peso);
                    } else {
                        System.err.println("Formato de linha inválido: " + linha);
                    }
                }
            }
        }
    }

    private void adicionarVerticeSeNaoExistir(T vertice) {
        if (!contemVertice(vertice)) {
            inserirVertice(vertice);
        }
    }


    private int pegarIndice(Vertice<T> v) {
        return vertices.indexOf(v);
    }



}