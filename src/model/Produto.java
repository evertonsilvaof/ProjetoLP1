package model;

public class Produto {


    private int id;
    private static int proximoId = 1001;
    private String nome;
    private String descricao;
    private double precoVenda;
    private int quantidadeEstoque;


    public Produto(String nome, String descricao, double precoVenda, int quantidadeEstoque) {
        this.id = proximoId++;
        this.nome = nome;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
    }


    public boolean baixarEstoque(int quantidadeVendida) {
        if (quantidadeVendida <= 0) {
            System.out.println("Erro: Quantidade inválida.");
            return false;
        }
        if (this.quantidadeEstoque >= quantidadeVendida) {
            this.quantidadeEstoque -= quantidadeVendida;
            return true;
        } else {
            System.out.println("Alerta de Estoque: Estoque insuficiente para " + nome + ". Disponível: " + this.quantidadeEstoque);
            return false;
        }
    }


    public void adicionarEstoque(int quantidadeRecebida) {
        this.quantidadeEstoque += quantidadeRecebida;
        System.out.println(quantidadeRecebida + " unidades de " + nome + " adicionadas ao estoque.");
    }



    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }



    @Override
    public String toString() {
        return "\nProduto [ID: " + id + ", Nome: " + nome + ", Preço: R$ " + String.format("%.2f", precoVenda) +
                ", Estoque: " + quantidadeEstoque + " unidades]";
    }
}