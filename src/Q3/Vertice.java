package Grafo.src.Q3;

import java.util.ArrayList;

public class Vertice <Interger> {
    private Interger dado;
    private ArrayList<Aresta<Interger>> arestasEntrada;
    private ArrayList<Aresta<Interger>> arestasSaida;

    public Vertice(Interger valor){
        this.dado = valor;
        this.arestasEntrada = new ArrayList<Aresta<Interger>> ();
        this.arestasSaida = new ArrayList<Aresta<Interger>> ();
    }

    public Interger getDado() {
        return dado;
    }

    public void setDado(Interger dado) {
        this.dado = dado;
    }
    public void adicionarArestaEntrada(Aresta<Interger> aresta){
        this.arestasEntrada.add(aresta);
    }
    public void adicionarArestaSaida(Aresta<Interger> aresta){
        this.arestasSaida.add(aresta);

    }

    public ArrayList<Aresta<Interger>> getArestasEntrada() {
        return arestasEntrada;
    }

    public void setArestasEntrada(ArrayList<Aresta<Interger>> arestasEntrada) {
        this.arestasEntrada = arestasEntrada;
    }

    public ArrayList<Aresta<Interger>> getArestasSaida() {
        return arestasSaida;
    }

    public void setArestasSaida(ArrayList<Aresta<Interger>> arestasSaida) {
        this.arestasSaida = arestasSaida;
    }
    public int contarLigacoes() {
        return this.arestasEntrada.size() + this.arestasSaida.size();
    }
}

