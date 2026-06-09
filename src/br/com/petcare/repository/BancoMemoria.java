package br.com.petcare.repository;

import br.com.petcare.model.Animal;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Prontuario;
import br.com.petcare.model.Tutor;

import java.util.ArrayList;
import java.util.List;

public class BancoMemoria {
    private List<Tutor> tutores = new ArrayList<>();
    private List<Animal> animais = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();
    private List<Prontuario> prontuarios = new ArrayList<>();

    public void salvarTutor(Tutor tutor) {
        tutores.add(tutor);
    }

    public void salvarAnimal(Animal animal) {
        animais.add(animal);
    }

    public void salvarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void salvarProntuario(Prontuario prontuario) {
        prontuarios.add(prontuario);
    }

    public List<Tutor> listarTutores() {
        return tutores;
    }

    public List<Animal> listarAnimais() {
        return animais;
    }

    public List<Consulta> listarConsultas() {
        return consultas;
    }

    public List<Prontuario> listarProntuarios() {
        return prontuarios;
    }

    public List<Prontuario> getProntuarios() {
        return prontuarios;
    }

    public Tutor buscarTutorPorId(int id) {
        for (Tutor tutor : tutores) {
            if (tutor.getId() == id) {
                return tutor;
            }
        }
        return null;
    }

    public Animal buscarAnimalPorId(int id) {
        for (Animal animal : animais) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }
}
