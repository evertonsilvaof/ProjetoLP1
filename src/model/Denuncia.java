package model;

import java.time.LocalDateTime;

public class Denuncia {

    // Atributos
    private int idDenuncia;
    private static int proximoId = 1;
    private LocalDateTime dataHora;
    private String localizacao;
    private String descricao;
    private String status;
    private String nomeDenunciante;
    private String contatoDenunciante;


    public Denuncia(String localizacao, String descricao, String nomeDenunciante, String contatoDenunciante) {
        this.idDenuncia = proximoId++;
        this.dataHora = LocalDateTime.now();
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.status = "Pendente"; // Status inicial
        this.nomeDenunciante = nomeDenunciante;
        this.contatoDenunciante = contatoDenunciante;
    }


    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
        System.out.println("✅ Status da Denúncia ID " + idDenuncia + " atualizado para: " + novoStatus);
    }



    public int getIdDenuncia() {
        return idDenuncia;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }



    @Override
    public String toString() {
        return "\n--- Detalhes da Denúncia (ID: " + idDenuncia + ") ---\n" +
                "Data/Hora: " + dataHora.toString().substring(0, 16) + "\n" +
                "Localização: " + localizacao + "\n" +
                "Descrição: " + descricao + "\n" +
                "Status: " + status + "\n" +
                "Denunciante: " + (nomeDenunciante.isEmpty() ? "Anônimo" : nomeDenunciante);
    }
}