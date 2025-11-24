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


        public void adicionarAnimal(Animal animal) {
            this.animais.add(animal);
            System.out.println("Animal " + animal.getNome() + " vinculado ao tutor " + this.nome + ".");
        }



        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public List<Animal> getAnimais() {
            return animais;
        }

        @Override
        public String toString() {
            return "\nTutor [CPF: " + cpf + ", Nome: " + nome + ", Telefone: " + telefone + ", Endereço: " + endereco +
                    ", Total de Animais: " + animais.size() + "]";
        }


    }

