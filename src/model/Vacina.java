package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vacina {
    private Animal animal;
    private String nomeVacina;
    private Veterinario veterinario;
    private LocalDateTime dataAplicacao;

    public Vacina(Animal animal, String nomeVacina, Veterinario veterinario) {
        this.animal = animal;
        this.nomeVacina = nomeVacina;
        this.veterinario = veterinario;
        this.dataAplicacao = LocalDateTime.now();
    }


    public Animal getAnimal() { return animal; }
    public String getNomeVacina() { return nomeVacina; }
    public LocalDateTime getDataAplicacao() { return dataAplicacao; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Vacina: %s | Animal: %s | Vet: %s | Data: %s",
                nomeVacina, animal.getNome(), veterinario.getNome(), dataAplicacao.format(fmt));
    }
}