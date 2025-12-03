package Service;
import model.Tutor;
import model.Veterinario;
import model.Animal;
import model.Atendimento;
import model.Produto;
import model.Venda;
import model.Denuncia;
import model.RegistroAdocao;
import model.Agenda;
import model.Vacina;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class PetService {


    private List<Tutor> tutores;
    private List<Veterinario> veterinarios;
    private List<Animal> animais;
    private List<Atendimento> atendimentos;
    private List<Produto> produtos;
    private List<Venda> vendas;
    private List<Denuncia> denuncias;
    private List<RegistroAdocao> registrosAdocao;
    private List<Agenda> agendamentos;
    private List<Vacina> vacinas;


    private static int proximoIdAnimal = 1;
    private static int proximoIdProduto = 1;

    public PetService() {
        this.tutores = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.animais = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.denuncias = new ArrayList<>();
        this.registrosAdocao = new ArrayList<>();
        this.agendamentos = new ArrayList<>();
        this.vacinas = new ArrayList<>();
    }



    public void cadastrarTutor(Tutor tutor) {
        if (buscarTutorPorCpf(tutor.getCpf()) == null) {
            this.tutores.add(tutor);
            System.out.println("Tutor " + tutor.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("Tutor com CPF " + tutor.getCpf() + " já existe.");
        }
    }

    public void cadastrarVeterinario(Veterinario vet) {

        this.veterinarios.add(vet);
        System.out.println("Veterinário " + vet.getNome() + " cadastrado com sucesso.");
    }

    public void cadastrarAnimal(Animal animal, Tutor tutor) {
        animal.setId(proximoIdAnimal++);
        this.animais.add(animal);
        tutor.adicionarAnimal(animal);
        System.out.println(" Animal " + animal.getNome() + " cadastrado (ID " + animal.getId() + ") e vinculado ao tutor.");
    }

    public void cadastrarProduto(Produto produto) {
        produto.setId(proximoIdProduto++);
        this.produtos.add(produto);
        System.out.println("Produto '" + produto.getNome() + "' cadastrado (ID " + produto.getId() + ").");
    }



    public Tutor buscarTutorPorCpf(String cpf) {
        return tutores.stream().filter(t -> t.getCpf().equals(cpf)).findFirst().orElse(null);
    }

    public Animal buscarAnimalPorId(int id) {
        return animais.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public Produto buscarProdutoPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Denuncia buscarDenunciaPorId(int id) {
        return denuncias.stream().filter(d -> d.getIdDenuncia() == id).findFirst().orElse(null);
    }




    public Venda realizarVenda(int produtoId, int quantidade, String cpfTutor, String metodoPagamento) {
        Produto produto = buscarProdutoPorId(produtoId);
        Tutor tutor = buscarTutorPorCpf(cpfTutor);

        if (produto == null) {
            System.out.println("Erro: Produto não encontrado.");
            return null;
        }
        if (tutor == null) {
            System.out.println("Erro: Tutor não encontrado.");
            return null;
        }
        if (produto.getEstoque() < quantidade) {
            System.out.println("Erro: Estoque insuficiente. Disponível: " + produto.getEstoque());
            return null;
        }


        produto.removerEstoque(quantidade);

        double valorTotal = produto.getPreco() * quantidade;
        Venda novaVenda = new Venda(produto, quantidade, tutor, metodoPagamento, valorTotal);
        this.vendas.add(novaVenda);

        System.out.println("Venda realizada! Total: R$ " + String.format("%.2f", valorTotal));
        return novaVenda;
    }


    public Agenda agendarConsulta(Animal animal, Veterinario veterinario, LocalDateTime dataHora, String observacoes) {
        Agenda novoAgendamento = new Agenda (animal, veterinario, dataHora, observacoes);
        this.agendamentos.add(novoAgendamento);
        System.out.println("Consulta agendada para " + animal.getNome() + " em " + dataHora.toString());
        return novoAgendamento;
    }


    public Atendimento registrarAtendimento(Animal animal, Veterinario veterinario, String diagnostico, String tratamento, double valor) {
        Atendimento novoAtendimento = new Atendimento(animal, veterinario, diagnostico, tratamento, valor);
        this.atendimentos.add(novoAtendimento);
        System.out.println("Atendimento registrado para " + animal.getNome());
        return novoAtendimento;
    }


    public void registrarVacinacao(Animal animal, String nomeVacina, Veterinario veterinario) {
        Vacina novaVacina = new Vacina(animal, nomeVacina, veterinario);
        this.vacinas.add(novaVacina);
        System.out.println("Vacinação registrada: " + nomeVacina + " para " + animal.getNome());
    }


    public boolean colocarAnimalParaAdocao(int animalId) {
        Animal animal = buscarAnimalPorId(animalId);
        if (animal != null) {
            animal.colocarParaAdocao();
            System.out.println(" " + animal.getNome() + " está agora disponível para adoção.");
            return true;
        }
        System.out.println("Animal não encontrado.");
        return false;
    }


    public RegistroAdocao concluirAdocao(int animalId, Tutor novoTutor) {
        Animal animal = buscarAnimalPorId(animalId);
        if (animal == null || !animal.isParaAdocao()) {
            System.out.println(" Erro: Animal não encontrado ou não disponível para adoção.");
            return null;
        }

        RegistroAdocao registro = new RegistroAdocao(animal, novoTutor);
        this.registrosAdocao.add(registro);


        animal.concluirAdocao(novoTutor);


        if (buscarTutorPorCpf(novoTutor.getCpf()) == null) {
            cadastrarTutor(novoTutor);
        }

        System.out.println("Adoção de " + animal.getNome() + " concluída com sucesso!");
        return registro;
    }




    private void verificarReincidencia(String localizacao) {
        boolean existe = denuncias.stream()
                .anyMatch(d -> d.getLocalizacao() != null && d.getLocalizacao().equalsIgnoreCase(localizacao));

        if (existe) {
            System.out.println("ALERTA DE REINCIDÊNCIA: Já existem denúncias anteriores para este local: " + localizacao);
        }
    }


    public Denuncia cadastrarDenuncia(String localizacao, String descricao, String nomeDenunciante, String contatoDenunciante) {

        verificarReincidencia(localizacao);

        Denuncia novaDenuncia = new Denuncia(localizacao, descricao, nomeDenunciante, contatoDenunciante);
        this.denuncias.add(novaDenuncia);
        System.out.println(" Denúncia ID " + novaDenuncia.getIdDenuncia() + " registrada.");
        return novaDenuncia;
    }


    public boolean atualizarStatusDenuncia(int denunciaId, String novoStatus) {
        Denuncia denuncia = buscarDenunciaPorId(denunciaId);
        if (denuncia != null) {
            denuncia.atualizarStatus(novoStatus);
            System.out.println("Status da denúncia atualizado para: " + novoStatus);
            return true;
        }
        System.out.println("Denúncia não encontrada.");
        return false;
    }



    public String gerarRelatorioEstoqueVendas() {
        double valorTotalEstoque = produtos.stream().mapToDouble(p -> p.getPreco() * p.getEstoque()).sum();
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- RELATÓRIO DE ESTOQUE ---\n");
        sb.append(String.format("Valor Total em Estoque: R$ %.2f\n", valorTotalEstoque));
        for (Produto p : produtos) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    public String gerarRelatorioDenunciasPorStatus() {
        if (denuncias.isEmpty()) return "Nenhuma denúncia registrada.";

        java.util.Map<String, Long> counts = denuncias.stream()
                .collect(Collectors.groupingBy(Denuncia::getStatus, Collectors.counting()));

        StringBuilder sb = new StringBuilder("\n--- DENÚNCIAS POR STATUS (RF014) ---\n");
        counts.forEach((status, count) -> sb.append(status).append(": ").append(count).append("\n"));
        return sb.toString();
    }


    public List<Tutor> getTutores() { return tutores; }
    public List<Veterinario> getVeterinarios() { return veterinarios; }
    public List<Animal> getAnimais() { return animais; }
    public List<Atendimento> getAtendimentos() { return atendimentos; }
    public List<Produto> getProdutos() { return produtos; }
    public List<Venda> getVendas() { return vendas; }
    public List<Denuncia> getDenuncias() { return denuncias; }
    public List<RegistroAdocao> getRegistrosAdocao() { return registrosAdocao; }
    public List<Agenda> getAgendamentos() { return agendamentos; }
    public List<Vacina> getVacinas() { return vacinas; }
}