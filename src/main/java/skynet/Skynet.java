package skynet;

import java.util.Scanner;

//Classe responsável por administrar o menu da aplicação
public class Skynet {
    //Atributos
    private SkynetGrafo skynet;

    //Atributos operacionais
    private Scanner scanner;
    private int opcao;
    private String dado;

    //Construtor
    public Skynet (){
        this.skynet = new SkynetGrafo("skynet/src/main/resources/grafo.txt");
        this.opcao = 0;
        this.scanner = new Scanner(System.in); //.useDelimiter("\n"); 
    }

    //Métodos
    public void iniciarAplicacao(){
        while (opcao != 7){
            imprimirMenuPrincipal();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    skynet.imprimirAdj();
                    break;
                case 2:
                    skynet.imprimirVertices();
                    break;
                case 3:
                    System.out.println("Informe o nome da cidade que deseja buscar:");
                    dado = scanner.next();
                    skynet.imprimirDadosCidade(dado);
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    System.out.println("Encerrando aplicação...");
                    break;
                default:
                    break;
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
