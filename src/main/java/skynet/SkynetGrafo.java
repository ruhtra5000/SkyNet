package skynet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import skynet.algoritmos.BuscaLargura;

//Grafo em si
public class SkynetGrafo {
    private ArrayList<Cidade> cidades;
    private ArrayList<LinkedList<Viagem>> linhasAereas;

    private BuscaLargura buscaLargura;

    public SkynetGrafo (String arquivo){
        this.cidades = new ArrayList<>();
        this.linhasAereas = new ArrayList<>();
        lerArquivo(arquivo);

        this.buscaLargura = new BuscaLargura(cidades, linhasAereas);
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
        //0 -> Nome; 1 -> País; 2 -> Descrição
        
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
            if(cidades.get(i).getNome().equals(nome))//melhorar
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
                    retornarNomeDoVertice(linhasAereas.get(i).get(j).getVertice()),
                    linhasAereas.get(i).get(j).getTempoViagem(),
                    linhasAereas.get(i).get(j).getPrecoPassagem());
                }
            }
            System.out.println();
        }
    }

    private String retornarNomeDoVertice (int index){
        return cidades.get(index).getNome();
    }

    public void imprimirVertices(){
        for (Cidade cidade : this.cidades){
            System.out.printf("%s, %s\n", cidade.getNome(), cidade.getPais());
        }
    }

    public void imprimirDadosCidade(String nome){
        int indexCidade = retornarIndiceVertice(nome);//pode lançar exceção
        System.out.printf("Cidade: %s, %s\n",cidades.get(indexCidade).getNome(),
                                             cidades.get(indexCidade).getPais());
        System.out.println("Descrição: " + cidades.get(indexCidade).getDescricao());

        System.out.println("\nVoos disponíveis:");
        for(Viagem v : linhasAereas.get(indexCidade)){
            System.out.printf("%s -> %s\n", nome, cidades.get(v.getVertice()).getNome());
            System.out.printf("Tempo de viagem: %.1f h\n", v.getTempoViagem());
            System.out.printf("Preço da passagem: R$ %.2f\n", v.getPrecoPassagem());
            System.out.println("============X============");
        }
    }

    //Realiza a chamada do método de busca em largura
    public void buscaEmLargura(String elemInicial, String elemFinal){
        int indexInicial = retornarIndiceVertice(elemInicial);
        int indexFinal = retornarIndiceVertice(elemFinal);
        
        this.buscaLargura.buscaEmLargura(indexInicial);

        System.out.printf("Caminho com menos conexões entre %s e %s:\n", elemInicial, elemFinal);
        this.buscaLargura.imprimirCaminho(indexInicial, indexFinal);
        System.out.print("\b\b\b   \n\n");
    }
}
