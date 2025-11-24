package Service;



import model.Animal;
import model.Tutor;
import model.Veterinario;
import java.util.ArrayList;
import java.util.List;

public class PetService {


    private List<Tutor> tutores;
    private List<Animal> animais;
    private List<Veterinario> veterinarios;


    public PetService() {
        this.tutores = new ArrayList<>();
        this.animais = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
    }


    public void cadastrarTutor(Tutor tutor) {

        for (Tutor t : tutores) {
            if (t.getCpf().equals(tutor.getCpf())) {
                System.out.println("Erro: Tutor com CPF " + tutor.getCpf() + " já cadastrado.");
                return;
            }
        }
        this.tutores.add(tutor);
        System.out.println("Tutor " + tutor.getNome() + " cadastrado com sucesso!");
    }


    public void cadastrarVeterinario(Veterinario vet) {

        for (Veterinario v : veterinarios) {
            if (v.getCrmv().equals(vet.getCrmv())) {
                System.out.println("Erro: Veterinário com CRMV " + vet.getCrmv() + " já cadastrado.");
                return;
            }
        }
        this.veterinarios.add(vet);
        System.out.println("Veterinário " + vet.getNome() + " cadastrado com sucesso!");
    }


    public void cadastrarAnimal(Animal animal, Tutor tutor) {
        this.animais.add(animal);
        tutor.adicionarAnimal(animal); // Vincula o animal ao tutor
        System.out.println("Animal " + animal.getNome() + " cadastrado e vinculado ao tutor " + tutor.getNome() + ".");
    }




    public Tutor buscarTutorPorCpf(String cpf) {
        for (Tutor t : tutores) {
            if (t.getCpf().equals(cpf)) {
                return t;
            }
        }
        return null;
    }


    public Animal buscarAnimalPorId(int id) {
        for (Animal a : animais) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }



    public List<Tutor> getTutores() {
        return tutores;
    }


}