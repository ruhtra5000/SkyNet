package skynet;

import java.util.Scanner;

//Classe responsável por administrar o menu da aplicação
public class Skynet {
    //Atributos
    private SkynetGrafo skynet;

    //Atributos operacionais
    private Scanner scanner;
    private int opcao;
    private String dado, dado2;

    //Construtor
    public Skynet (){
        this.skynet = new SkynetGrafo("skynet/src/main/resources/grafo.txt");
        this.scanner = new Scanner(System.in);
        this.opcao = 0;
        this.dado = "";
        this.dado2 = "";
    }

    //Métodos
    public void iniciarAplicacao(){
        while (opcao != 7){
            try {
                imprimirMenuPrincipal();
                opcao = Integer.parseInt(scanner.nextLine());
    
                switch (opcao) {
                    case 1:
                        skynet.imprimirAdj();
                        break;
                    case 2:
                        skynet.imprimirVertices();
                        break;
                    case 3:
                        System.out.println("Informe o nome da cidade que deseja buscar:");
                        dado = scanner.nextLine();
                        skynet.imprimirDadosCidade(dado);
                        break;
                    case 4:
                        System.out.println("Informe a cidade de partida: ");
                        dado = scanner.nextLine();
                        System.out.println("Informe a cidade de chegada: ");
                        dado2 = scanner.nextLine();
                        skynet.caminhoCurtoPreco(dado, dado2);
                        break;
                    case 5:
                        System.out.println("Informe a cidade de partida: ");
                        dado = scanner.nextLine();
                        System.out.println("Informe a cidade de chegada: ");
                        dado2 = scanner.nextLine();
                        skynet.caminhoCurtoTempo(dado, dado2);
                        break;
                    case 6:
                        System.out.println("Informe a cidade de partida: ");
                        dado = scanner.nextLine();
                        System.out.println("Informe a cidade de chegada: ");
                        dado2 = scanner.nextLine();
                        skynet.buscaEmLargura(dado, dado2);
                        break;
                    case 7:
                        System.out.println("Encerrando aplicação...");
                        break;
                    default:
                        break;
                }
            }
            catch(Exception err) {
                System.out.println("Erro: " + err.getMessage());
            }
        }
    }

    private void imprimirMenuPrincipal(){
        System.out.println("<===== SKYNET =====>");
        System.out.println("1. Imprimir grafo");
        System.out.println("2. Listar cidades");
        System.out.println("3. Consultar cidade e voos disponíveis");
        System.out.println("4. Buscar rota mais barata");
        System.out.println("5. Buscar rota mais rápida");
        System.out.println("6. Buscar rota com menos conexões");
        System.out.println("7. Sair");
        System.out.print("Comando: ");
    }
}
