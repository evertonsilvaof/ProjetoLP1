package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Denuncia {
    private static int proximoId = 1;
    private int idDenuncia;
    private LocalDateTime dataRegistro;
    private String localizacao;
    private String descricao;
    private String nomeDenunciante;
    private String contatoDenunciante;
    private String status;

    public Denuncia(String localizacao, String descricao, String nomeDenunciante, String contatoDenunciante) {
        this.idDenuncia = proximoId++;
        this.dataRegistro = LocalDateTime.now();
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.nomeDenunciante = nomeDenunciante.isEmpty() ? "Anônimo" : nomeDenunciante;
        this.contatoDenunciante = contatoDenunciante;
        this.status = "Pendente";
    }


    public int getIdDenuncia() { return idDenuncia; }
    public String getStatus() { return status; }


    public String getLocalizacao() { return localizacao; }

    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("ID: %d | Data: %s | Status: %s | Local: %s | Denunciante: %s",
                idDenuncia, dataRegistro.format(fmt), status, localizacao, nomeDenunciante);
    }
}