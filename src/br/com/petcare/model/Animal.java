package br.com.petcare.model;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private int id;
    private String nome;
    private int idade;
    private Tutor tutor;
    private List<Vacina> vacinas = new ArrayList<>();

    public Animal(int id, String nome, int idade, Tutor tutor) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.tutor = tutor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<Vacina> getVacinas() {
    return vacinas;
}

    public void adicionarVacina(Vacina vacina) {
    vacinas.add(vacina);
}

    public abstract String getEspecie();

    public String emitirSom() {
        return "Som genérico de animal";
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", especie='" + getEspecie() + '\'' +
                ", tutor='" + tutor.getNome() + '\'' +
                '}';
    }
}
