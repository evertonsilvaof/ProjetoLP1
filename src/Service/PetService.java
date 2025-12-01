package Service;



import model.Animal;
import model.Tutor;
import model.Veterinario;
import model.Atendimento;
import model.Produto;
import model.Venda;
import model.Denuncia;
import java.util.ArrayList;
import java.util.List;


public class PetService {


    private List<Tutor> tutores;
    private List<Animal> animais;
    private List<Veterinario> veterinarios;
    private List<Atendimento> atendimentos;
    private List<Produto> produtos;
    private List<Venda> vendas;
    private List<Denuncia> denuncias;


    public PetService() {
        this.tutores = new ArrayList<>();
        this.animais = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.denuncias = new ArrayList<>();

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



public Atendimento registrarAtendimento (Animal animal, Veterinario vet, String diagnostico, String tratamento, double valor) {

    Atendimento novoAtendimento = new Atendimento(animal, vet, diagnostico, tratamento, valor);

    this.atendimentos.add(novoAtendimento);

    System.out.println("Atendimento ID " + novoAtendimento.getIdAtendimento() + " registrado para " + animal.getNome() + ".");

    return novoAtendimento;
}
    public void cadastrarProduto(Produto produto) {

        this.produtos.add(produto);
        System.out.println(" Produto '" + produto.getNome() + "' cadastrado com sucesso! ID: " + produto.getId());
    }


    public List<Produto> getProdutos() {
        return produtos;
    }


    public Produto buscarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean realizarVenda(int idProduto, int quantidade, String cpfTutor, String formaPagamento) {

        Produto produtoVendido = buscarProdutoPorId(idProduto);
        Tutor tutorComprador = buscarTutorPorCpf(cpfTutor);

        if (produtoVendido == null) {
            System.out.println("Erro de Venda: Produto com ID " + idProduto + " não encontrado.");
            return false;
        }


        if (produtoVendido.baixarEstoque(quantidade)) {


            List<Produto> itensVenda = new ArrayList<>();
            itensVenda.add(produtoVendido);

            Venda novaVenda = new Venda(tutorComprador, itensVenda, formaPagamento);
            this.vendas.add(novaVenda);

            System.out.println(" Venda ID " + novaVenda.getIdVenda() + " realizada com sucesso. Total: R$ " + String.format("%.2f", novaVenda.getValorTotal()));
            return true;

        } else {

            System.out.println("Venda cancelada devido a restrição de estoque.");
            return false;
        }
    }


    public List<Venda> getVendas() {
        return vendas;
    }


    public List<Veterinario> getVeterinarios() {

        return veterinarios;
    }
    public List<Atendimento> getAtendimentos() {

        return atendimentos;
    }
    public Denuncia cadastrarDenuncia(String localizacao, String descricao, String nomeDenunciante, String contatoDenunciante) {

        Denuncia novaDenuncia = new Denuncia(localizacao, descricao, nomeDenunciante, contatoDenunciante);
        this.denuncias.add(novaDenuncia);

        System.out.println(" Denúncia ID " + novaDenuncia.getIdDenuncia() + " registrada com status 'Pendente'.");
        return novaDenuncia;
    }


    public Denuncia buscarDenunciaPorId(int id) {
        for (Denuncia d : denuncias) {
            if (d.getIdDenuncia() == id) {
                return d;
            }
        }
        return null;
    }


    public List<Denuncia> getDenuncias() {
        return denuncias;
    }






}