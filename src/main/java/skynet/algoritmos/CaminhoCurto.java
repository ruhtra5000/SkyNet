package skynet.algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;

import skynet.Cidade;
import skynet.Viagem;

//Algoritmo de Bellman-Ford, conseguindo calcular o caminho mais curto
//em relação ao tempo e ao preço das passagens.
public class CaminhoCurto {
    //Atributos
    private ArrayList<Cidade> cidades;
    private ArrayList<LinkedList<Viagem>> linhasAereas;
    
    private int [] predecessores;
    private double [] distancia;

    //Construtor
    public CaminhoCurto (ArrayList<Cidade> cidades, ArrayList<LinkedList<Viagem>> linhasAereas){
        this.cidades = cidades;
        this.linhasAereas = linhasAereas;

        this.predecessores = new int[cidades.size()];
        this.distancia = new double[cidades.size()];
    }

    //Caminho mais curto em questão de tempo
    public int [] caminhoCurtoTempo(String elementoInicial){
        int elemIndex = retornarIndiceVertice(elementoInicial);

        iniciarElementos(elemIndex);

        //Algoritmo em si
        for(int k = 1; k < cidades.size()-1; k++){
            for(int i = 0; i < cidades.size(); i++){
                for(int j = 0; j < linhasAereas.get(i).size(); j++){
                    relaxamentoTempo(i, j);
                }
            }
        }

        //Checagem por ciclos de peso positivo
        int jIndexReal;
        for(int i = 0; i < cidades.size(); i++){
            for(int j = 0; j < linhasAereas.get(i).size(); j++){
                jIndexReal = linhasAereas.get(i).get(j).getVertice();
                if(distancia[jIndexReal] > (distancia[i] + linhasAereas.get(i).get(j).getTempoViagem())){
                    System.out.println("dist[j]: " + distancia[j]);
                    System.out.println("dist[i] + lA[i][j]: " + distancia[i] + " + " + linhasAereas.get(i).get(j).getTempoViagem());
                    return null;
                }
            }
        }
        return predecessores;
    }

    private void iniciarElementos(int elemIndex){
        for(int i = 0; i < cidades.size(); i++){
            predecessores[i] = -1; //pred. nulo
            distancia[i] = Double.POSITIVE_INFINITY;
        }
        distancia[elemIndex] = 0;
    }

    private void relaxamentoTempo(int ini, int fim){
        int fimIndexReal = linhasAereas.get(ini).get(fim).getVertice();
        if(distancia[fimIndexReal] > (distancia[ini] + linhasAereas.get(ini).get(fim).getTempoViagem())){
            distancia[fimIndexReal] = distancia[ini] + linhasAereas.get(ini).get(fim).getTempoViagem();
            predecessores[fimIndexReal] = ini;
        }
    }

    public void imprimirCaminhoMaisCurtoTempo(String ini, String fim){
        if(predecessores == null){   
            System.out.println("Há um ciclo de peso positivo no grafo");
            return;
        }
        
        int iniIndex = retornarIndiceVertice(ini);
        int fimIndex = retornarIndiceVertice(fim);

        System.out.printf("Caminho mais rapido entre %s e %s\n", ini, fim);
        buscarCaminhoMaisCurtoTempo(iniIndex, fimIndex);
        System.out.print("\b\b\b   \n\n");
    }

    private void buscarCaminhoMaisCurtoTempo(int ini, int fim){
        if(fim == ini)
            System.out.printf("%s (%.1fh) -> ", cidades.get(ini).getNome(), distancia[ini]);
        else if(predecessores[fim] == -1) //predecessor nulo
            System.out.println("Não há caminho entre " + cidades.get(ini).getNome() + 
                               " e " + cidades.get(fim).getNome());
        else {
            buscarCaminhoMaisCurtoTempo(ini, predecessores[fim]);
            System.out.printf("%s (%.1fh) -> ", cidades.get(fim).getNome(), distancia[fim]);
        }
    }

    //Caminho mais curto em questão de preço 
    public int [] caminhoCurtoPreco(String elementoInicial){
        int elemIndex = retornarIndiceVertice(elementoInicial);

        iniciarElementos(elemIndex);

        //Algoritmo em si
        for(int k = 1; k < cidades.size()-1; k++){
            for(int i = 0; i < cidades.size(); i++){
                for(int j = 0; j < linhasAereas.get(i).size(); j++){
                    relaxamentoPreco(i, j);
                }
            }
        }

        //Checagem por ciclos de peso positivo
        int jIndexReal;
        for(int i = 0; i < cidades.size(); i++){
            for(int j = 0; j < linhasAereas.get(i).size(); j++){
                jIndexReal = linhasAereas.get(i).get(j).getVertice();
                if(distancia[jIndexReal] > (distancia[i] + linhasAereas.get(i).get(j).getPrecoPassagem())){
                    System.out.println("dist[j]: " + distancia[j]);
                    System.out.println("dist[i] + lA[i][j]: " + distancia[i] + " + " + linhasAereas.get(i).get(j).getPrecoPassagem());
                    return null;
                }
            }
        }
        return predecessores;
    }

    private void relaxamentoPreco(int ini, int fim){
        int fimIndexReal = linhasAereas.get(ini).get(fim).getVertice();
        if(distancia[fimIndexReal] > (distancia[ini] + linhasAereas.get(ini).get(fim).getPrecoPassagem())){
            distancia[fimIndexReal] = distancia[ini] + linhasAereas.get(ini).get(fim).getPrecoPassagem();
            predecessores[fimIndexReal] = ini;
        }
    }

    public void imprimirCaminhoMaisCurtoPreco(String ini, String fim){
        if(predecessores == null){   
            System.out.println("Há um ciclo de peso positivo no grafo");
            return;
        }
        
        int iniIndex = retornarIndiceVertice(ini);
        int fimIndex = retornarIndiceVertice(fim);

        System.out.printf("Caminho mais barato entre %s e %s\n", ini, fim);
        buscarCaminhoMaisCurtoPreco(iniIndex, fimIndex);
        System.out.print("\b\b\b   \n\n");
    }

    private void buscarCaminhoMaisCurtoPreco(int ini, int fim){
        if(fim == ini)
            System.out.printf("%s (R$%.2f) -> ", cidades.get(ini).getNome(), distancia[ini]);
        else if(predecessores[fim] == -1) //predecessor nulo
            System.out.println("Não há caminho entre " + cidades.get(ini).getNome() + 
                               " e " + cidades.get(fim).getNome());
        else {
            buscarCaminhoMaisCurtoPreco(ini, predecessores[fim]);
            System.out.printf("%s (R$%.2f) -> ", cidades.get(fim).getNome(), distancia[fim]);
        }
    }

    //Função auxiliar
    private int retornarIndiceVertice(String nome){
        for (int i = 0; i < cidades.size(); i++){
            if(cidades.get(i).getNome().equals(nome))//melhorar
                return i;
        }
        return -1;
    }
}
