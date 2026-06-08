package br.com.petcare.service;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Cachorro;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Gato;
import br.com.petcare.model.StatusConsulta;
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
}