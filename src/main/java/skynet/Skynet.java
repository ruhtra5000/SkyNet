package skynet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

//Grafo em si
public class Skynet {
    private ArrayList<Cidade> cidades;
    private ArrayList<LinkedList<Viagem>> linhasAereas;

    public Skynet (String arquivo){
        this.cidades = new ArrayList<>();
        this.linhasAereas = new ArrayList<>();
        lerArquivo(arquivo);
    }

    //Metodos
    //Gerar o grafo
    public void lerArquivo (String arquivo) {
        int [] indices = {0, 0};//V, E
        String linha;
        try {
            File arq = new File(arquivo);
            Scanner scanner = new Scanner(arq);
            for (int linhaNum = 0; scanner.hasNextLine(); linhaNum++){
                linha = scanner.nextLine();
                if(linhaNum == 0){
                    indices = preencherIndices(linha);
                }
                else if(linhaNum > 0 && linhaNum <= indices[0]){
                    adicionarVertice(linha);
                }
                else {
                    adicionarAresta(linha);
                }
            }
            scanner.close();
        }
        catch(FileNotFoundException err){
            System.out.println("Erro: " + err.getMessage());
        }
    }

    private int [] preencherIndices (String linha){
        int [] ind = {0, 0};
        String [] temp = linha.split(" ");
        ind[0] = Integer.parseInt(temp[0]);
        ind[1] = Integer.parseInt(temp[1]);
        return ind;
    }

    private void adicionarVertice(String linha) {
        String [] linhaDividida = linha.split(";");
        Cidade c = new Cidade(linhaDividida[0], linhaDividida[1], linhaDividida[2]);
        //0 -> nome; 1 -> País; 2 -> Descrição
        
        cidades.add(c);
        linhasAereas.add(new LinkedList<>());
    }

    private void adicionarAresta(String linha) {
        String [] div = linha.split(";");
        int posicao1 = retornarIndiceVertice(div[0]);
        int posicao2 = retornarIndiceVertice(div[1]);
        double tempoViagem = Double.parseDouble(div[2]);
        double precoViagem = Double.parseDouble(div[3]);

        Viagem v1 = new Viagem(posicao1, tempoViagem, precoViagem);
        Viagem v2 = new Viagem(posicao2, tempoViagem, precoViagem);

        linhasAereas.get(posicao1).add(v2);
        linhasAereas.get(posicao2).add(v1);
    }

    private int retornarIndiceVertice(String nome){
        for (int i = 0; i < cidades.size(); i++){
            if(cidades.get(i).getNome().equals(nome))
                return i;
        }
        return -1;
    }

    //Imprimir o grafo
    public void imprimirAdj(){
        System.out.println("Lista de adjacencia:");
        for (int i = 0; i < cidades.size(); i++){
            System.out.print(cidades.get(i).getNome() + ": ");
            if(!linhasAereas.get(i).isEmpty()){
                for (int j = 0; j < linhasAereas.get(i).size(); j++){
                    System.out.printf("[%s, %.1f, %.0f] ", 
                    retornarNomeDoNo(linhasAereas.get(i).get(j).getVertice()),
                    linhasAereas.get(i).get(j).getTempoViagem(),
                    linhasAereas.get(i).get(j).getPrecoPassagem());
                }
            }
            System.out.println();
        }
    }

    private String retornarNomeDoNo (int index){
        return cidades.get(index).getNome();
    }
}
