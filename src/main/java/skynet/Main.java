package skynet;

public class Main {
    public static void main(String[] args) {
        Skynet skynet = new Skynet("skynet/src/main/resources/grafo.txt");
        skynet.imprimirAdj();
    }
}