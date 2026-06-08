package br.com.petcare.model;

import java.time.LocalDate;

public class Vacina {

    private int id;
    private String nomeVacina;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
    private Animal animal;

    public Vacina(int id, String nomeVacina, LocalDate dataAplicacao,
                  LocalDate dataProximaDose, Animal animal) {
        this.id = id;
        this.nomeVacina = nomeVacina;
        this.dataAplicacao = dataAplicacao;
        this.dataProximaDose = dataProximaDose;
        this.animal = animal;
    }

    public int getId() {
        return id;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public LocalDate getDataProximaDose() {
        return dataProximaDose;
    }

    public void setDataProximaDose(LocalDate dataProximaDose) {
        this.dataProximaDose = dataProximaDose;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}