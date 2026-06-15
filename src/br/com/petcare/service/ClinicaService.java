package br.com.petcare.service;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Atendimento;
import br.com.petcare.model.Cachorro;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Gato;
import br.com.petcare.model.StatusConsulta;
import br.com.petcare.model.Tutor;
import br.com.petcare.repository.BancoMemoria;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class ClinicaService {

    private BancoMemoria banco;
    private int proximoIdTutor = 1;
    private int proximoIdAnimal = 1;
    private int proximoIdConsulta = 1;

    // --- Feature 07: Cálculo de pagamento ---
    private int proximoIdServico = 1;
    private int proximoIdAtendimento = 1;

    public ClinicaService(BancoMemoria banco) {
        this.banco = banco;
    }

    // ========== Tutores ==========

    public Tutor cadastrarTutor(String nome, String telefone, String email) {
        if (textoVazio(nome)) {
            throw new IllegalArgumentException("Nome do tutor não pode ser vazio.");
        }

        if (textoVazio(telefone)) {
            throw new IllegalArgumentException("Telefone não pode ser vazio.");
        }

        Tutor tutor = new Tutor(proximoIdTutor++, nome, telefone, email);
        banco.salvarTutor(tutor);
        return tutor;
    }

    public List<Tutor> listarTutores() {
        return banco.listarTutores();
    }

    public Tutor buscarTutorPorId(int id) {
        return banco.buscarTutorPorId(id);
    }

    // ========== Animais ==========

    public Cachorro cadastrarCachorro(String nome, int idade, Tutor tutor, String porte) {
        validarAnimal(nome, idade);

        Cachorro cachorro = new Cachorro(proximoIdAnimal++, nome, idade, tutor, porte);
        banco.salvarAnimal(cachorro);
        return cachorro;
    }

    public Gato cadastrarGato(String nome, int idade, Tutor tutor, boolean castrado) {
        validarAnimal(nome, idade);

        Gato gato = new Gato(proximoIdAnimal++, nome, idade, tutor, castrado);
        banco.salvarAnimal(gato);
        return gato;
    }

    public List<Animal> listarAnimais() {
        return banco.listarAnimais();
    }

    public Animal buscarAnimalPorId(int id) {
        return banco.buscarAnimalPorId(id);
    }

    // ========== Consultas ==========

    public Consulta agendarConsulta(Animal animal, LocalDateTime dataHora, String motivo) {
        if (animal == null) {
            throw new IllegalArgumentException("Consulta não pode ser agendada sem animal.");
        }

        if (textoVazio(motivo)) {
            throw new IllegalArgumentException("Motivo da consulta não pode ser vazio.");
        }

        Consulta consulta = new Consulta(proximoIdConsulta++, animal, dataHora, motivo);
        banco.salvarConsulta(consulta);
        return consulta;
    }

    public List<Consulta> listarConsultas() {
        return banco.listarConsultas();
    }

    // ========== Serviços (Feature 07) ==========

    public ServicoClinica cadastrarServico(String nome, String descricao, double valor, int duracaoEmMinutos) {
        ServicoClinica servico = new ServicoClinica(proximoIdServico++, nome, descricao, valor, duracaoEmMinutos);
        banco.salvarServico(servico);
        return servico;
    }

    public List<ServicoClinica> listarServicos() {
        return banco.listarServicos();
    }

    public ServicoClinica buscarServicoPorId(int id) {
        return banco.buscarServicoPorId(id);
    }

    // ========== Atendimentos (Feature 07) ==========

    public Atendimento abrirAtendimento(Animal animal, LocalDateTime data) {
        Atendimento atendimento = new Atendimento(proximoIdAtendimento++, animal, data);
        banco.salvarAtendimento(atendimento);
        return atendimento;
    }

    public void adicionarServicoAoAtendimento(Atendimento atendimento, ServicoClinica servico) {
        atendimento.adicionarServico(servico);
    }

    public boolean removerServicoDoAtendimento(Atendimento atendimento, int idServico) {
        return atendimento.removerServico(idServico);
    }

    public double calcularValorTotalAtendimento(Atendimento atendimento) {
        return atendimento.getValorTotal();
    }

    public List<Atendimento> listarAtendimentos() {
        return banco.listarAtendimentos();
    }

    public Atendimento buscarAtendimentoPorId(int id) {
        return banco.buscarAtendimentoPorId(id);
    }

    private void validarAnimal(String nome, int idade) {
        if (textoVazio(nome)) {
            throw new IllegalArgumentException("Nome do animal não pode ser vazio.");
        }

        if (idade < 0) {
            throw new IllegalArgumentException("Idade do animal não pode ser negativa.");
        }
    }

    private boolean textoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    // Feature 09 — Cancelamento de Consulta
    public String cancelarConsulta(int id) {
        for (Consulta c : banco.listarConsultas()) {
            if (c.getId() == id) {
                if (c.getStatus() == StatusConsulta.REALIZADA) {
                    return "Erro: consulta #" + id + " já foi REALIZADA e não pode ser cancelada.";
                }
                if (c.getStatus() == StatusConsulta.CANCELADA) {
                    return "Erro: consulta #" + id + " já está CANCELADA.";
                }
                c.setStatus(StatusConsulta.CANCELADA);
                return "Consulta #" + id + " cancelada com sucesso.";
            }
        }
        return "Erro: consulta #" + id + " não encontrada.";
    }

    public List<Animal> buscarAnimaisPorNome(String nome) {
        List<Animal> resultado = new ArrayList<>();

        for (Animal animal : banco.listarAnimais()) {
            if (animal.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(animal);
            }
        }

        return resultado;
    }

    public List<Animal> buscarAnimaisPorEspecie(String especie) {
        List<Animal> resultado = new ArrayList<>();

        for (Animal animal : banco.listarAnimais()) {
            if (animal.getEspecie().equalsIgnoreCase(especie)) {
                resultado.add(animal);
            }
        }

        return resultado;
    }

    public List<Animal> buscarAnimaisPorNomeTutor(String nomeTutor) {
        List<Animal> resultado = new ArrayList<>();

        for (Animal animal : banco.listarAnimais()) {
            if (animal.getTutor().getNome()
                    .toLowerCase()
                    .contains(nomeTutor.toLowerCase())) {

                resultado.add(animal);
            }
        }

        return resultado;
    }
}
