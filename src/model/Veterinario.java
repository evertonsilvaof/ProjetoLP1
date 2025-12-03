package model;

public class Veterinario {
    private String crmv;
    private String nome;
    private String cpf;
    private String telefone;

    public Veterinario(String crmv, String nome, String cpf, String telefone) {
        this.crmv = crmv;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }


    public String getCrmv() { return crmv; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return String.format("CRMV: %s | Nome: %s | CPF: %s | Tel: %s",
                crmv, nome, cpf, telefone);
    }
}