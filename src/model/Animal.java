package model;


import java.util.ArrayList;
import java.util.List;

public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private Tutor tutor;
    private boolean paraAdocao;

    public Animal(String nome, String especie, String raca, int idade, Tutor tutor) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.tutor = tutor;
        this.paraAdocao = false;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public Tutor getTutor() { return tutor; }
    public boolean isParaAdocao() { return paraAdocao; }


    public void colocarParaAdocao() {
        this.paraAdocao = true;
    }

    public void concluirAdocao(Tutor novoTutor) {
        this.paraAdocao = false;

        if (this.tutor != null) {
            this.tutor.removerAnimal(this);
        }
        this.tutor = novoTutor;
        novoTutor.adicionarAnimal(this);
    }

    @Override
    public String toString() {
        String statusAdocao = paraAdocao ? " [DISPONÍVEL PARA ADOÇÃO]" : "";
        String nomeTutor = (tutor != null) ? tutor.getNome() : "N/A";
        return String.format("ID: %d | Nome: %s | Espécie: %s | Raça: %s | Idade: %d | Tutor: %s%s",
                id, nome, especie, raca, idade, nomeTutor, statusAdocao);
    }
}