package controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;


import Service.PetService;
import model.Tutor;
import model.Veterinario;
import model.Animal;
import model.Produto;
import model.Denuncia;

public class PetController {

    private PetService service;
    private Scanner scanner;

    public PetController() {
        this.service = new PetService();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("--- BEM-VINDO AO PET SYSTEM  ---");
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();
            try {
                processarOpcao(opcao);
            } catch (Exception e) {
                System.out.println(" Erro: " + e.getMessage());
            }
        } while (opcao != 0);

        System.out.println("A encerrar o sistema...");
        scanner.close();
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Cadastros (Tutor, Animal, Vet)");
        System.out.println("2. Pet Shop (Produtos e Vendas)");
        System.out.println("3. Veterinária (Agendamento, Atendimento, Vacina)");
        System.out.println("4. Adoção Responsável");
        System.out.println("5. Proteção Animal (Denúncias)");
        System.out.println("6. Relatórios Gerenciais");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Opção inválida. Digite um número: ");
        }
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: menuCadastros(); break;
            case 2: menuPetShop(); break;
            case 3: menuVeterinaria(); break;
            case 4: menuAdocao(); break;
            case 5: menuDenuncias(); break;
            case 6: menuRelatorios(); break;
            case 0: break;
            default: System.out.println("Opção inválida.");
        }
    }



    private void menuCadastros() {
        System.out.println("\n--- CADASTROS ---");
        System.out.println("1. Cadastrar Tutor");
        System.out.println("2. Cadastrar Veterinário");
        System.out.println("3. Cadastrar Animal");
        System.out.println("4. Listar Todos");
        System.out.print("Opção: ");
        int op = lerOpcao();

        switch (op) {
            case 1: cadastrarTutor(); break;
            case 2: cadastrarVeterinario(); break;
            case 3: cadastrarAnimal(); break;
            case 4: listarCadastros(); break;
        }
    }

    private void cadastrarTutor() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Telefone: "); String tel = scanner.nextLine();
        System.out.print("Endereço: "); String end = scanner.nextLine();
        service.cadastrarTutor(new Tutor(cpf, nome, tel, end));
    }

    private void cadastrarVeterinario() {
        System.out.print("CRMV: "); String crmv = scanner.nextLine();
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        System.out.print("Telefone: "); String tel = scanner.nextLine();
        service.cadastrarVeterinario(new Veterinario(crmv, nome, cpf, tel));
    }

    private void cadastrarAnimal() {
        System.out.print("CPF do Tutor Responsável: "); String cpf = scanner.nextLine();
        Tutor t = service.buscarTutorPorCpf(cpf);
        if (t == null) { System.out.println(" Tutor não encontrado. Cadastre-o primeiro."); return; }

        System.out.print("Nome do Animal: "); String nome = scanner.nextLine();
        System.out.print("Espécie: "); String esp = scanner.nextLine();
        System.out.print("Raça: "); String raca = scanner.nextLine();
        System.out.print("Idade: "); int idade = scanner.nextInt(); scanner.nextLine();

        service.cadastrarAnimal(new Animal(nome, esp, raca, idade, t), t);
    }

    private void listarCadastros() {
        System.out.println("\n--- LISTAGEM GERAL ---");
        System.out.println(">> TUTORES:");
        service.getTutores().forEach(System.out::println);
        System.out.println(">> VETERINÁRIOS:");
        service.getVeterinarios().forEach(System.out::println);
        System.out.println(">> ANIMAIS:");
        service.getAnimais().forEach(System.out::println);
    }

    private void menuPetShop() {
        System.out.println("\n--- PET SHOP ---");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Realizar Venda");
        System.out.println("3. Listar Produtos");
        System.out.print("Opção: ");
        int op = lerOpcao();

        if (op == 1) {
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("Descrição: "); String desc = scanner.nextLine();
            System.out.print("Preço: "); double preco = scanner.nextDouble();
            System.out.print("Estoque Inicial: "); int est = scanner.nextInt(); scanner.nextLine();
            service.cadastrarProduto(new Produto(nome, desc, preco, est));
        } else if (op == 2) {
            realizarVenda();
        } else if (op == 3) {
            service.getProdutos().forEach(System.out::println);
        }
    }

    private void realizarVenda() {
        if (service.getProdutos().isEmpty()) { System.out.println("Sem produtos cadastrados."); return; }
        service.getProdutos().forEach(p -> System.out.println("ID: " + p.getId() + " - " + p.getNome() + " (R$" + p.getPreco() + ")"));

        System.out.print("ID do Produto: "); int idProd = scanner.nextInt();
        System.out.print("Quantidade: "); int qtd = scanner.nextInt(); scanner.nextLine();
        System.out.print("CPF do Comprador: "); String cpf = scanner.nextLine();
        System.out.print("Método Pagamento: "); String pag = scanner.nextLine();

        service.realizarVenda(idProd, qtd, cpf, pag);
    }

    private void menuVeterinaria() {
        System.out.println("\n--- MÓDULO VETERINÁRIA ---");
        System.out.println("1. Agendar Consulta");
        System.out.println("2. Registrar Atendimento");
        System.out.println("3. Registrar Vacinação");
        System.out.println("4. Listar Agendamentos");
        System.out.println("5. Listar Vacinas");
        System.out.print("Opção: ");
        int op = lerOpcao();

        switch(op) {
            case 1: agendarConsulta(); break;
            case 2: registrarAtendimento(); break;
            case 3: registrarVacinacao(); break;
            case 4: service.getAgendamentos().forEach(System.out::println); break;
            case 5: service.getVacinas().forEach(System.out::println); break;
        }
    }

    private void agendarConsulta() {
        System.out.print("ID Animal: "); int idAni = scanner.nextInt(); scanner.nextLine();
        Animal a = service.buscarAnimalPorId(idAni);
        if (a == null) { System.out.println("Animal não encontrado."); return; }

        System.out.print("CRMV Veterinário: "); String crmv = scanner.nextLine();
        Veterinario v = service.getVeterinarios().stream()
                .filter(vet -> vet.getCrmv().equalsIgnoreCase(crmv))
                .findFirst().orElse(null);

        if (v == null) { System.out.println("Veterinário não encontrado."); return; }

        System.out.print("Data/Hora (dd/MM/yyyy HH:mm): "); String dataStr = scanner.nextLine();
        try {
            LocalDateTime data = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            System.out.print("Observações: "); String obs = scanner.nextLine();
            service.agendarConsulta(a, v, data, obs);
        } catch (DateTimeParseException e) {
            System.out.println(" Formato de data inválido. Use dia/mês/ano hora:minuto.");
        }
    }

    private void registrarAtendimento() {
        System.out.print("ID Animal: "); int idAni = scanner.nextInt(); scanner.nextLine();
        Animal a = service.buscarAnimalPorId(idAni);

        System.out.print("CRMV Veterinário: "); String crmv = scanner.nextLine();
        Veterinario v = service.getVeterinarios().stream()
                .filter(vet -> vet.getCrmv().equalsIgnoreCase(crmv))
                .findFirst().orElse(null);

        if (a != null && v != null) {
            System.out.print("Diagnóstico: "); String diag = scanner.nextLine();
            System.out.print("Tratamento: "); String trat = scanner.nextLine();
            System.out.print("Valor Consulta: "); double val = scanner.nextDouble(); scanner.nextLine();
            service.registrarAtendimento(a, v, diag, trat, val);
        } else {
            System.out.println("Animal ou Veterinário inválido.");
        }
    }

    private void registrarVacinacao() {
        System.out.print("ID Animal: "); int idAni = scanner.nextInt(); scanner.nextLine();
        Animal a = service.buscarAnimalPorId(idAni);

        System.out.print("CRMV Veterinário: "); String crmv = scanner.nextLine();
        Veterinario v = service.getVeterinarios().stream()
                .filter(vet -> vet.getCrmv().equalsIgnoreCase(crmv))
                .findFirst().orElse(null);

        if (a != null && v != null) {
            System.out.print("Nome da Vacina: "); String vacina = scanner.nextLine();
            service.registrarVacinacao(a, vacina, v);
        } else {
            System.out.println(" Animal ou Veterinário inválido.");
        }
    }

    private void menuAdocao() {
        System.out.println("\n--- ADOÇÃO ---");
        System.out.println("1. Marcar Animal para Adoção");
        System.out.println("2. Concluir Adoção");
        System.out.println("3. Listar Animais Disponíveis");
        int op = lerOpcao();

        if (op == 1) {
            System.out.print("ID Animal: "); service.colocarAnimalParaAdocao(scanner.nextInt());
        } else if (op == 2) {
            System.out.print("ID Animal: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("CPF Novo Tutor: "); String cpf = scanner.nextLine();
            Tutor t = service.buscarTutorPorCpf(cpf);
            if (t == null) {
                System.out.println("Tutor não encontrado. Cadastre-o primeiro no menu principal.");
            } else {
                service.concluirAdocao(id, t);
            }
        } else if (op == 3) {
            service.getAnimais().stream().filter(Animal::isParaAdocao).forEach(System.out::println);
        }
    }

    private void menuDenuncias() {
        System.out.println("\n--- DENÚNCIAS ---");
        System.out.println("1. Nova Denúncia");
        System.out.println("2. Atualizar Status");
        System.out.println("3. Listar Denúncias");
        int op = lerOpcao();

        if (op == 1) {
            System.out.print("Localização: "); String loc = scanner.nextLine();
            System.out.print("Descrição do fato: "); String desc = scanner.nextLine();
            System.out.print("Nome Denunciante (Enter para anônimo): "); String nome = scanner.nextLine();
            System.out.print("Contato (Opcional): "); String contato = scanner.nextLine();
            service.cadastrarDenuncia(loc, desc, nome, contato);
        } else if (op == 2) {
            System.out.print("ID Denúncia: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Novo Status: "); String st = scanner.nextLine();
            service.atualizarStatusDenuncia(id, st);
        } else if (op == 3) {
            service.getDenuncias().forEach(System.out::println);
        }
    }

    private void menuRelatorios() {
        System.out.println("\n--- RELATÓRIOS GERENCIAIS ---");
        System.out.println(service.gerarRelatorioEstoqueVendas());
        System.out.println(service.gerarRelatorioDenunciasPorStatus());

        System.out.println("\nHistórico de Adoções:");
        service.getRegistrosAdocao().forEach(System.out::println);

        System.out.println("\nHistórico de Atendimentos:");
        service.getAtendimentos().forEach(System.out::println);
    }
}