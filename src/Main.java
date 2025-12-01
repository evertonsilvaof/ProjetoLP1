import model.Animal;
import model.Tutor;
import model.Veterinario;
import model.Produto;
import model.Atendimento;
import model.Denuncia;
import Service.PetService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- PET SYSTEM - SIMULAÇÃO COMPLETA (RF016) ---");


        PetService service = new PetService();


        System.out.println("\n==============================================");
        System.out.println("1. CADASTRO DE TUTORES E VETERINÁRIOS (RF001, RF003)");
        System.out.println("==============================================");

        Tutor tutor1 = new Tutor("111.111.111-11", "Everton Fernandes", "(83) 9999-0000", "Rua 1");
        Tutor tutor2 = new Tutor("222.222.222-22", "Laila Batista", "(83) 8888-1111", "Rua 2");
        Veterinario vet1 = new Veterinario("CRMV/RN 00000", "Dra. Joana", "333.333.333-33", "(83) 7777-2222");


        service.cadastrarTutor(tutor1);
        service.cadastrarTutor(tutor2);
        service.cadastrarVeterinario(vet1);

        System.out.println("\nTutores cadastrados na base de dados (PetService):");
        service.getTutores().forEach(System.out::println);



        System.out.println("\n==============================================");
        System.out.println("2. CADASTRO DE ANIMAIS E VINCULAÇÃO (RF002)");
        System.out.println("==============================================");


        Animal animalA = new Animal("Pingo", "Cachorro", "poodle", 5, tutor1);
        Animal animalB = new Animal("CACAU", "Cachorro", "Akita", 2, tutor1);
        Animal animalC = new Animal("Mel", "Cachorro", "Vira-lata", 1, tutor2);


        service.cadastrarAnimal(animalA, tutor1);
        service.cadastrarAnimal(animalB, tutor1);
        service.cadastrarAnimal(animalC, tutor2);

        System.out.println("\nAnimais cadastrados:");
        System.out.println(animalA);
        System.out.println(animalB);


        System.out.println("\n==============================================");
        System.out.println("3. SIMULAÇÃO DE ATENDIMENTO VETERINÁRIO (RF005)");
        System.out.println("==============================================");


        Animal animalParaConsulta = service.buscarAnimalPorId(animalB.getId());
        Veterinario vetParaConsulta = service.getVeterinarios().get(0); // Pega o primeiro Vet

        if (animalParaConsulta != null && vetParaConsulta != null) {
            System.out.println("-> Tentando registrar atendimento para " + animalParaConsulta.getNome());


            service.registrarAtendimento(
                    animalParaConsulta,
                    vetParaConsulta,
                    "Doença do carrapato",
                    "Comprimido Doxixilina por 14 dias.",
                    120.00
            );
        }


        System.out.println("\n--- Histórico de Atendimentos (Após RF005) ---");
        for (Atendimento a : service.getAtendimentos()) {
            System.out.println(a);
        }


        System.out.println("\n==============================================");
        System.out.println("4. CADASTRO DE PRODUTOS (RF009)");
        System.out.println("==============================================");

        Produto racao = new Produto("Ração Standard", "Ração de 10kg para cães adultos.", 95.00, 50);
        Produto brinquedo = new Produto("Bola ", "Bola colorida para brincadeiras.", 15.00, 100);

        service.cadastrarProduto(racao);
        service.cadastrarProduto(brinquedo);

        System.out.println("\nProdutos em Estoque (Antes da Venda):");
        service.getProdutos().forEach(System.out::println);


        System.out.println("\n==============================================");
        System.out.println("5. PROCESSO DE VENDA (RF010)");
        System.out.println("==============================================");


        System.out.println("-> Tentativa de Venda 1: 15 unidades de Ração.");
        service.realizarVenda(racao.getId(), 15, tutor1.getCpf(), "PIX");


        System.out.println("\n-> Tentativa de Venda 2: 200 unidades de Bola.");
        service.realizarVenda(brinquedo.getId(), 200, tutor2.getCpf(), "Cartão");


        System.out.println("\n--- Verificação Final de Estoque (Após RF010) ---");

        System.out.println(service.buscarProdutoPorId(racao.getId()));

        System.out.println("\n--- Histórico de Vendas (Após RF010) ---");
        service.getVendas().forEach(System.out::println);


        System.out.println("\n==============================================");
        System.out.println("6. CADASTRO E ATUALIZAÇÃO DE DENÚNCIAS (RF012, RF013)");
        System.out.println("==============================================");

        Denuncia denuncia1 = service.cadastrarDenuncia(
                "Rua das Palmeiras, 100, Bairro Centro",
                "Cão de pequeno porte preso em corrente curta sem água fresca. Latidos constantes.",
                "Testemunha A",
                "(83) 9999-5555"
        );

        Denuncia denuncia2 = service.cadastrarDenuncia(
                "Condomínio Flores, Bloco B",
                "Gato abandonado na área comum, parece doente.",
                "", // Denúncia anônima
                ""
        );


        System.out.println("\n--- Atualização de Status de Denúncia ---");


        Denuncia denunciaEmInvestigacao = service.buscarDenunciaPorId(denuncia1.getIdDenuncia());
        if (denunciaEmInvestigacao != null) {
            denunciaEmInvestigacao.atualizarStatus("Em Investigação");
        }


        System.out.println("\n--- Relatório de Denúncias (Após RF013) ---");
        service.getDenuncias().forEach(System.out::println);
    }
}