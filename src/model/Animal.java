package model;

public class Animal {

    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private Tutor tuto;


    private boolean vacinado;
    private boolean paraAdocao;


    private static int proximoId = 1;


    public Animal(String nome, String especie, String raca, int idade, Tutor tutor) {
        this.id = proximoId++;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.tuto = tutor;
        this.vacinado = false;
        this.paraAdocao = false;
    }


    public void registrarVacinacao() {
        this.vacinado = true;
        System.out.println(this.nome + " foi registrado como vacinado.");
    }


    public void colocarParaAdocao() {
        this.tuto = null;
        this.paraAdocao = true;
        System.out.println(this.nome + " está agora disponível para adoção.");
    }



    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public Tutor getTutor() {
        return tuto;
    }

    public void setTutor(Tutor tutor) {
        this.tuto = tutor;
    }

    public boolean isParaAdocao() {
        return paraAdocao;
    }

    public void setParaAdocao(boolean paraAdocao) {
        this.paraAdocao = paraAdocao;
    }

    @Override
    public String toString() {
        String infoTutor = (tuto != null) ? tuto.getNome() : "Nenhum (Para Adoção)";
        return "\nAnimal [ID: " + id + ", Nome: " + nome + ", Espécie: " + especie +
                ", Idade: " + idade + ", Tutor: " + infoTutor +
                ", Vacinado: " + vacinado + ", Para Adoção: " + paraAdocao + "]";
    }
}