package Grafo.src.Q3;

public class Aresta<Interger> {
    private Double peso;
    private Vertice<Interger> inicio;
    private Vertice<Interger> fim;

    public Aresta(Double peso, Vertice<Interger> inicio, Vertice<Interger> fim) {
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Aresta(Vertice<Interger> inicio, Vertice<Interger> fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Vertice<Interger> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice<Interger> inicio) {
        this.inicio = inicio;
    }

    public Vertice<Interger> getFim() {
        return fim;
    }

    public void setFim(Vertice<Interger> fim) {
        this.fim = fim;
    }
}
