package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {


    private int idVenda;
    private static int proximoId = 1;
    private LocalDateTime dataHora;
    private Tutor tutorComprador;
    private List<Produto> itensVendidos;
    private double valorTotal;
    private String formaPagamento;


    public Venda(Tutor tutorComprador, List<Produto> itensVendidos, String formaPagamento) {
        this.idVenda = proximoId++;
        this.dataHora = LocalDateTime.now();
        this.tutorComprador = tutorComprador;
        this.itensVendidos = itensVendidos;
        this.formaPagamento = formaPagamento;
        this.valorTotal = calcularValorTotal();
    }


    private double calcularValorTotal() {
        double total = 0;
        for (Produto item : itensVendidos) {

            total += item.getPrecoVenda();
        }
        return total;
    }



    public int getIdVenda() {
        return idVenda;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Tutor getTutorComprador() {
        return tutorComprador;
    }

    public List<Produto> getItensVendidos() {
        return itensVendidos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        String nomeTutor = (tutorComprador != null) ? tutorComprador.getNome() : "Não Informado";

        return "\n--- Registro de Venda (ID: " + idVenda + ") ---\n" +
                "Data/Hora: " + dataHora.toString().substring(0, 16) + "\n" +
                "Cliente: " + nomeTutor + "\n" +
                "Itens: " + itensVendidos.size() + " produtos" + "\n" +
                "Valor Total: R$ " + String.format("%.2f", valorTotal) + "\n" +
                "Pagamento: " + formaPagamento;
    }
}