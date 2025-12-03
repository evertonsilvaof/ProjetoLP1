package model;

import java.util.ArrayList;
import java.util.List;

public class Tutor {
    private String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private List<Animal> animais;

    public Tutor(String cpf, String nome, String telefone, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.animais = new ArrayList<>();
    }


    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public List<Animal> getAnimais() { return animais; }


    public void adicionarAnimal(Animal animal) {
        if (!this.animais.contains(animal)) {
            this.animais.add(animal);
        }
    }

    public void removerAnimal(Animal animal) {
        this.animais.remove(animal);
    }

    @Override
    public String toString() {
        return String.format("CPF: %s | Nome: %s | Tel: %s | Endereço: %s | Animais (%d)",
                cpf, nome, telefone, endereco, animais.size());
    }
}