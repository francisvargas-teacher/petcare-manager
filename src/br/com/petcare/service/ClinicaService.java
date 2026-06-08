package br.com.petcare.service;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Atendimento;
import br.com.petcare.model.Cachorro;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Gato;
import br.com.petcare.model.ServicoClinica;
import br.com.petcare.model.Tutor;
import br.com.petcare.repository.BancoMemoria;

import java.time.LocalDateTime;
import java.util.List;

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
        Cachorro cachorro = new Cachorro(proximoIdAnimal++, nome, idade, tutor, porte);
        banco.salvarAnimal(cachorro);
        return cachorro;
    }

    public Gato cadastrarGato(String nome, int idade, Tutor tutor, boolean castrado) {
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
}
