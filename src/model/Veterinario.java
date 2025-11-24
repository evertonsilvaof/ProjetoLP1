package model;


public class Veterinario {

    private String crmv; // Identificação única e essencial
    private String nome;
    private String cpf;
    private String telefone;


    public Veterinario(String crmv, String nome, String cpf, String telefone) {
        this.crmv = crmv;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }


    public void realizarAtendimento(Animal animal, String diagnostico, String tratamento) {
        System.out.println("\n--- Atendimento Realizado ---");
        System.out.println("Veterinário: " + this.nome + " (CRMV: " + this.crmv + ")");
        System.out.println("Animal Atendido: " + animal.getNome());
        System.out.println("Diagnóstico: " + diagnostico);
        System.out.println("Tratamento: " + tratamento);

    }



    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "\nVeterinário [CRMV: " + crmv + ", Nome: " + nome + ", CPF: " + cpf + ", Telefone: " + telefone + "]";
    }
}