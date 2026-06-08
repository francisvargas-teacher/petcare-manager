package br.com.petcare.model;

public class Gato extends Animal {
    private boolean castrado;

    public Gato(int id, String nome, int idade, Tutor tutor, boolean castrado) {
        super(id, nome, idade, tutor);
        this.castrado = castrado;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    @Override
    public String getEspecie() {
        return "Gato";
    }

    @Override
    public String emitirSom() {
        return "Miau";
    }
}
