package skynet;

//Arestas do grafo
public class Viagem {
    //Atributos
    private int vertice;
    private double tempoViagem; //Tempo descrito em horas
    private double precoPassagem; //Preço em reais

    //Construtor
    public Viagem (int vertice, double tempoViagem, double precoPassagem){
        this.vertice = vertice;
        this.tempoViagem = tempoViagem;
        this.precoPassagem = precoPassagem;
    }

    //Getters e Setters
    public int getVertice() {
        return this.vertice;
    }
    
    public double getTempoViagem() {
        return this.tempoViagem;
    }
    public void setTempoViagem(double tempoViagem) {
        this.tempoViagem = tempoViagem;
    }

    public double getPrecoPassagem() {
        return this.precoPassagem;
    }
    public void setPrecoPassagem(double precoPassagem) {
        this.precoPassagem = precoPassagem;
    }
}
