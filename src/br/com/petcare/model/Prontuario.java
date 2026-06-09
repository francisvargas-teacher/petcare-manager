package br.com.petcare.model;

import java.time.LocalDate;

public class Prontuario {
    private int id;
    private Animal animal;
    private LocalDate dataRegistro;
    private String descricao;
    private String nomeVeterinario;

    public Prontuario(int id, Animal animal, LocalDate dataRegistro, String descricao, String nomeVeterinario) {
        this.id = id;
        this.animal = animal;
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.nomeVeterinario = nomeVeterinario;
    }

    public int getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNomeVeterinario() {
        return nomeVeterinario;
    }

    @Override
    public String toString() {
        return "Prontuario{" +
                "id=" + id +
                ", animal='" + animal.getNome() + '\'' +
                ", tutor='" + animal.getTutor().getNome() + '\'' +
                ", dataRegistro=" + dataRegistro +
                ", descricao='" + descricao + '\'' +
                ", nomeVeterinario='" + nomeVeterinario + '\'' +
                '}';
    }
}
