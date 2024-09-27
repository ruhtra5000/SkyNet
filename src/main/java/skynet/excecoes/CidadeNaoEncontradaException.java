package skynet.excecoes;

//Exceção personalizada
public class CidadeNaoEncontradaException extends Exception {
    
    //Construtor
    public CidadeNaoEncontradaException(){
        super("A cidade não foi encontrada! Possivelmente, houve um erro de digitação.");
    }
}
