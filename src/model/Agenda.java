package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Agenda {
    private Animal animal;
    private Veterinario veterinario;
    private LocalDateTime dataHora;
    private String observacoes;

    public Agenda(Animal animal, Veterinario veterinario, LocalDateTime dataHora, String observacoes) {
        this.animal = animal;
        this.veterinario = veterinario;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
    }


    public LocalDateTime getDataHora() { return dataHora; }
    public Animal getAnimal() { return animal; }
    public Veterinario getVeterinario() { return veterinario; }
    public String getObservacoes() { return observacoes; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Agendamento: %s | Animal: %s | Vet: %s | Obs: %s",
                dataHora.format(formatter),
                animal.getNome(),
                veterinario.getNome(),
                observacoes);
    }
}