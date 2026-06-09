package br.com.petcare.service;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Cachorro;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Gato;
import br.com.petcare.model.Prontuario;
import br.com.petcare.model.Tutor;
import br.com.petcare.repository.BancoMemoria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClinicaService {
    private BancoMemoria banco;
    private int proximoIdTutor = 1;
    private int proximoIdAnimal = 1;
    private int proximoIdConsulta = 1;
    private int proximoIdProntuario = 1;

    public ClinicaService(BancoMemoria banco) {
        this.banco = banco;
    }

    public Tutor cadastrarTutor(String nome, String telefone, String email) {
        Tutor tutor = new Tutor(proximoIdTutor++, nome, telefone, email);
        banco.salvarTutor(tutor);
        return tutor;
    }

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

    public Consulta agendarConsulta(Animal animal, LocalDateTime dataHora, String motivo) {
        Consulta consulta = new Consulta(proximoIdConsulta++, animal, dataHora, motivo);
        banco.salvarConsulta(consulta);
        return consulta;
    }

    public Prontuario registrarProntuario(Animal animal, String descricao, String nomeVeterinario) {
        Prontuario prontuario = new Prontuario(
                proximoIdProntuario++,
                animal,
                LocalDate.now(),
                descricao,
                nomeVeterinario
        );
        banco.salvarProntuario(prontuario);
        return prontuario;
    }

    public List<Tutor> listarTutores() {
        return banco.listarTutores();
    }

    public List<Animal> listarAnimais() {
        return banco.listarAnimais();
    }

    public List<Consulta> listarConsultas() {
        return banco.listarConsultas();
    }

    public List<Prontuario> listarProntuarios() {
        return banco.listarProntuarios();
    }

    public List<Prontuario> listarProntuariosPorAnimal(Animal animal) {
        List<Prontuario> prontuariosDoAnimal = new ArrayList<>();

        for (Prontuario prontuario : banco.listarProntuarios()) {
            if (prontuario.getAnimal() == animal) {
                prontuariosDoAnimal.add(prontuario);
            }
        }

        return prontuariosDoAnimal;
    }

    public Animal buscarAnimalPorId(int id) {
        return banco.buscarAnimalPorId(id);
    }

    public Tutor buscarTutorPorId(int id) {
        return banco.buscarTutorPorId(id);
    }
}
