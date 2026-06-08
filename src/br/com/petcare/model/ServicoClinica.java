package br.com.petcare.model;

public class ServicoClinica {

    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private int duracaoEmMinutos;

    public ServicoClinica(int id, String nome, String descricao, double valor, int duracaoEmMinutos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.duracaoEmMinutos = duracaoEmMinutos;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    @Override
    public String toString() {
        return "ServicoClinica{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=R$" + String.format("%.2f", valor) +
                ", duracaoEmMinutos=" + duracaoEmMinutos +
                '}';
    }
}
