package br.com.petcare.repository;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Atendimento;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.ServicoClinica;
import br.com.petcare.model.Tutor;

import java.util.ArrayList;
import java.util.List;

public class BancoMemoria {

    private List<Tutor> tutores = new ArrayList<>();
    private List<Animal> animais = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();

    // --- Feature 07: Cálculo de pagamento ---
    private List<ServicoClinica> servicos = new ArrayList<>();
    private List<Atendimento> atendimentos = new ArrayList<>();

    // ========== Tutores ==========

    public void salvarTutor(Tutor tutor) {
        tutores.add(tutor);
    }

    public List<Tutor> listarTutores() {
        return tutores;
    }

    public Tutor buscarTutorPorId(int id) {
        for (Tutor tutor : tutores) {
            if (tutor.getId() == id) {
                return tutor;
            }
        }
        return null;
    }

    // ========== Animais ==========

    public void salvarAnimal(Animal animal) {
        animais.add(animal);
    }

    public List<Animal> listarAnimais() {
        return animais;
    }

    public Animal buscarAnimalPorId(int id) {
        for (Animal animal : animais) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    // ========== Consultas ==========

    public void salvarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public List<Consulta> listarConsultas() {
        return consultas;
    }

    // ========== Serviços (Feature 07) ==========

    public void salvarServico(ServicoClinica servico) {
        servicos.add(servico);
    }

    public List<ServicoClinica> listarServicos() {
        return servicos;
    }

    public ServicoClinica buscarServicoPorId(int id) {
        for (ServicoClinica servico : servicos) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        return null;
    }

    // ========== Atendimentos (Feature 07) ==========

    public void salvarAtendimento(Atendimento atendimento) {
        atendimentos.add(atendimento);
    }

    public List<Atendimento> listarAtendimentos() {
        return atendimentos;
    }

    public Atendimento buscarAtendimentoPorId(int id) {
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getId() == id) {
                return atendimento;
            }
        }
        return null;
    }
}
