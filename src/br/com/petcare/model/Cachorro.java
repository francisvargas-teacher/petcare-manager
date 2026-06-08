package br.com.petcare.model;

public class Cachorro extends Animal {
    private String porte;

    public Cachorro(int id, String nome, int idade, Tutor tutor, String porte) {
        super(id, nome, idade, tutor);
        this.porte = porte;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    @Override
    public String getEspecie() {
        return "Cachorro";
    }

    @Override
    public String emitirSom() {
        return "Au au";
    }
}
