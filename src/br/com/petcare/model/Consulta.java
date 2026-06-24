package br.com.petcare.model;

import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private Animal animal;
    private LocalDateTime dataHora;
    private String motivo;
    private String observacao;
    private StatusConsulta status;

    public Consulta(int id, Animal animal, LocalDateTime dataHora, String motivo) {
        this.id = id;
        this.animal = animal;
        this.dataHora = dataHora;
        this.motivo = motivo;
        this.observacao = "";
        this.status = StatusConsulta.AGENDADA;
    }

    public int getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", animal='" + animal.getNome() + '\'' +
                ", tutor='" + animal.getTutor().getNome() + '\'' +
                ", dataHora=" + dataHora +
                ", motivo='" + motivo + '\'' +
                ", observacao='" + observacao + '\'' +
                ", status=" + status +
                '}';
    }
}