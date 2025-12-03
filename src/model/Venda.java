package model;

import java.time.LocalDateTime;

public class Venda {
    private Produto produtoVendido;
    private int quantidade;
    private Tutor comprador;
    private LocalDateTime dataVenda;
    private String metodoPagamento;
    private double valorTotal;

    public Venda(Produto produtoVendido, int quantidade, Tutor comprador, String metodoPagamento, double valorTotal) {
        this.produtoVendido = produtoVendido;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.dataVenda = LocalDateTime.now();
        this.metodoPagamento = metodoPagamento;
        this.valorTotal = valorTotal;
    }


    public Produto getProdutoVendido() { return produtoVendido; }
    public int getQuantidade() { return quantidade; }
    public LocalDateTime getDataVenda() { return dataVenda; }
    public double getValorTotal() { return valorTotal; }

    @Override
    public String toString() {
        return String.format("Venda em %s | Produto: %s (x%d) | Comprador: %s | Total: R$ %.2f",
                dataVenda.toLocalDate(), produtoVendido.getNome(), quantidade, comprador.getNome(), valorTotal);
    }
}