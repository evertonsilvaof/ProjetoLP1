package model;

import java.time.LocalDateTime;

public class RegistroAdocao {
    private Animal animalAdotado;
    private Tutor novoTutor;
    private LocalDateTime dataAdocao;

    public RegistroAdocao(Animal animalAdotado, Tutor novoTutor) {
        this.animalAdotado = animalAdotado;
        this.novoTutor = novoTutor;
        this.dataAdocao = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("Adoção em %s | Animal: %s (ID %d) | Novo Tutor: %s (CPF %s)",
                dataAdocao.toLocalDate(), animalAdotado.getNome(), animalAdotado.getId(), novoTutor.getNome(), novoTutor.getCpf());
    }
}