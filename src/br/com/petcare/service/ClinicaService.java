package br.com.petcare.service;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Cachorro;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Gato;
import br.com.petcare.model.Tutor;
import br.com.petcare.repository.BancoMemoria;

import java.time.LocalDateTime;
import java.util.List;

public class ClinicaService {
    private BancoMemoria banco;
    private int proximoIdTutor = 1;
    private int proximoIdAnimal = 1;
    private int proximoIdConsulta = 1;

    public ClinicaService(BancoMemoria banco) {
        this.banco = banco;
    }

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

    public List<Tutor> listarTutores() {
        return banco.listarTutores();
    }

    public List<Animal> listarAnimais() {
        return banco.listarAnimais();
    }

    public List<Consulta> listarConsultas() {
        return banco.listarConsultas();
    }

    public Animal buscarAnimalPorId(int id) {
        return banco.buscarAnimalPorId(id);
    }

    public Tutor buscarTutorPorId(int id) {
        return banco.buscarTutorPorId(id);
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
}