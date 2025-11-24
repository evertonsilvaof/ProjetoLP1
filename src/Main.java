import model.Animal;
import model.Tutor;
import model.Veterinario;



public class Main {
    public static void main(String[] args) {
        System.out.println("--- PET SYSTEM  ---");



        Tutor tutor1 = new Tutor("111.111.111-11", "Everton Fernandes", "(83) 9999-0000", "Rua 1");
        Tutor tutor2 = new Tutor("222.222.222-22", "Laila Batista", "(83) 8888-1111", "Rua 2");
        System.out.println("\n--- Cadastro de Tutores ---");
        System.out.println(tutor1);
        System.out.println(tutor2);


        Veterinario vet1 = new Veterinario("CRMV/RN 00000", "Dra. Joana", "333.333.333-33", "(83) 7777-2222");
        System.out.println("\n--- Cadastro de Veterinários ---");
        System.out.println(vet1);




        Animal animalA = new Animal("Pingo", "Cachorro", "poodle", 5, tutor1);
        Animal animalB = new Animal("CACAU", "Cachorro", "Akita", 2, tutor1);
        Animal animalC = new Animal("Mel", "Cachorro", "Vira-lata", 1, tutor2);


        tutor1.adicionarAnimal(animalA);
        tutor1.adicionarAnimal(animalB);
        tutor2.adicionarAnimal(animalC);

        System.out.println("\n--- Animais Cadastrados ---");
        System.out.println(animalA);
        System.out.println(animalB);
        System.out.println(animalC);




        animalA.registrarVacinacao();
        System.out.println(animalA);


        animalC.colocarParaAdocao();
        System.out.println(animalC);


        vet1.realizarAtendimento(animalB, "Doença do carrapato", "Comprimido.");

        // O sistema está em produção ainda;
    }
}

