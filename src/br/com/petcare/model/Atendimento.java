package br.com.petcare.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Atendimento {

    private int id;
    private Animal animal;
    private List<ServicoClinica> servicos;
    private LocalDateTime data;
    private double valorTotal;

    public Atendimento(int id, Animal animal, LocalDateTime data) {
        this.id = id;
        this.animal = animal;
        this.data = data;
        this.servicos = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void adicionarServico(ServicoClinica servico) {
        servicos.add(servico);
        recalcularValorTotal();
    }

    public boolean removerServico(int idServico) {
        boolean removido = servicos.removeIf(s -> s.getId() == idServico);
        if (removido) {
            recalcularValorTotal();
        }
        return removido;
    }

    private void recalcularValorTotal() {
        valorTotal = servicos.stream()
                .mapToDouble(ServicoClinica::getValor)
                .sum();
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

    public List<ServicoClinica> getServicos() {
        return servicos;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Atendimento{")
                .append("id=").append(id)
                .append(", animal='").append(animal.getNome()).append('\'')
                .append(", tutor='").append(animal.getTutor().getNome()).append('\'')
                .append(", data=").append(data)
                .append(", servicos=[");

        for (int i = 0; i < servicos.size(); i++) {
            sb.append(servicos.get(i).getNome());
            if (i < servicos.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]")
                .append(", valorTotal=R$").append(String.format("%.2f", valorTotal))
                .append('}');

        return sb.toString();
    }
}
