package model;
import java.time.LocalDateTime;

public class Atendimento {


    private int idAtendimento;
    private static int proximoId = 1;
    private Animal animal;
    private Veterinario veterinario;
    private LocalDateTime dataHora;
    private String diagnostico;
    private String tratamento;
    private double valorConsulta;


    public Atendimento(Animal animal, Veterinario veterinario, String diagnostico, String tratamento, double valorConsulta) {
        this.idAtendimento = proximoId++;
        this.animal = animal;
        this.veterinario = veterinario;
        this.dataHora = LocalDateTime.now();
        this.diagnostico = diagnostico;
        this.tratamento = tratamento;
        this.valorConsulta = valorConsulta;
    }



    public int getIdAtendimento() {
        return idAtendimento;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamento() {
        return tratamento;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }


    @Override
    public String toString() {
        return "\n--- Registro de Atendimento (ID: " + idAtendimento + ") ---\n" +
                "Data/Hora: " + dataHora.toString().substring(0, 16) + "\n" +
                "Animal: " + animal.getNome() + " (ID: " + animal.getId() + ")\n" +
                "Veterinário: " + veterinario.getNome() + " (CRMV: " + veterinario.getCrmv() + ")\n" +
                "Diagnóstico: " + diagnostico + "\n" +
                "Tratamento: " + tratamento + "\n" +
                "Valor: R$ " + String.format("%.2f", valorConsulta);
    }
}