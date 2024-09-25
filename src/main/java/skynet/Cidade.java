package skynet;

//Vértices do grafo
public class Cidade {
    //Atributos
    private String nome;
    private String pais;
    private String descricao;

    //Construtor
    public Cidade (String nome, String pais, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    
    //Getters e Setters
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return this.pais; 
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}


