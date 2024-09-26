package skynet.algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import skynet.Cidade;
import skynet.Viagem;

public class BuscaLargura {
    //Atributos
    private ArrayList<Cidade> cidades;
    private ArrayList<LinkedList<Viagem>> linhasAereas;

    private char [] cor;
    private int [] predecessor;
    private int [] distancia;
    private Queue <Integer> fila;

    //Construtor
    public BuscaLargura(ArrayList<Cidade> cidades, ArrayList<LinkedList<Viagem>> linhasAereas){
        this.cidades = cidades;
        this.linhasAereas = linhasAereas;

        //Inicializando fila e arrays necessários
        this.cor = new char[cidades.size()];
        this.predecessor = new int[cidades.size()];
        this.distancia = new int[cidades.size()];
        this.fila = new LinkedList<>();
    }

    //Algoritmo de busca em largura
    public void buscaEmLargura(int indexInicial) {
        //Todos os elementos iniciam pintados de branco
        //E sua distancia sendo "infinito", e predecessores nulos
        for (int i = 0; i < cidades.size(); i++){
            cor[i] = 'B'; 
            predecessor[i] = -1;//predecessor nulo
            distancia[i] = -1;//distância infinita
        }

        //Inicializando o vertice de partida
        fila.add(indexInicial);
        cor[indexInicial] = 'C';
        distancia[indexInicial] = 0;

        while(!fila.isEmpty()){
            int indexElemAtual = fila.remove();
            //Passando pelos elementos da lista de adj. atual
            for(Viagem v : linhasAereas.get(indexElemAtual)){
                int i = v.getVertice();
                if(cor[i] == 'B'){
                    cor[i] = 'C';
                    predecessor[i] = indexElemAtual;
                    distancia[i] = distancia[predecessor[i]] + 1;
                    fila.add(i);
                }
            }
            cor[indexElemAtual] = 'P';
        }
    }

    public void imprimirCaminho(int ini, int fim){
        if(fim == ini) {
            System.out.print(cidades.get(ini).getNome() + " -> ");
        }
        else if(predecessor[fim] == -1) {//predecessor nulo
            System.out.printf("Não há caminho entre %s e %s\n", 
                              cidades.get(ini).getNome(), cidades.get(fim).getNome());
        } 
        else {
            imprimirCaminho(ini, predecessor[fim]);
            System.out.print(cidades.get(fim).getNome() + " -> ");
        }
    }

}
