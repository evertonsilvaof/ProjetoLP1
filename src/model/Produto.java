package model;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;

    public Produto(String nome, String descricao, double preco, int estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }


    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }


    public void setId(int id) {
        this.id = id;
    }


    public void removerEstoque(int quantidade) {
        this.estoque -= quantidade;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Produto: %s | Preço: R$ %.2f | Estoque: %d",
                id, nome, preco, estoque);
    }
}