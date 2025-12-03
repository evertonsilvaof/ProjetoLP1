package model;

import java.time.LocalDateTime;

public class Atendimento {
    private Animal animal;
    private Veterinario veterinario;
    private LocalDateTime dataHora;
    private String diagnostico;
    private String tratamento;
    private double valor;

    public Atendimento(Animal animal, Veterinario veterinario, String diagnostico, String tratamento, double valor) {
        this.animal = animal;
        this.veterinario = veterinario;
        this.dataHora = LocalDateTime.now();
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.valor = valor;
    }


    public double getValor() { return valor; }

    @Override
    public String toString() {
        return String.format("Data: %s | Animal: %s (ID %d) | Vet: %s | Valor: R$ %.2f",
                dataHora.toLocalDate(), animal.getNome(), animal.getId(), veterinario.getNome(), valor);
    }
}