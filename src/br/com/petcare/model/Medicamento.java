package br.com.petcare.model;

import java.time.LocalDate;

public class Medicamento {
    private int id;
    private String nome;
    private String descricao;
    private String fabricante;
    private String concentracao;
    private int quantidadeEmEstoque;
    private int estoqueMinimo;
    private LocalDate dataValidade;

    public Medicamento(
            int id,
            String nome,
            String descricao,
            String fabricante,
            String concentracao,
            int quantidadeEmEstoque,
            int estoqueMinimo,
            LocalDate dataValidade
    ) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.concentracao = concentracao;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.estoqueMinimo = estoqueMinimo;
        this.dataValidade = dataValidade;
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

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(String concentracao) {
        this.concentracao = concentracao;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de entrada deve ser maior que zero.");
        }

        this.quantidadeEmEstoque += quantidade;
    }

    public void retirarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de saída deve ser maior que zero.");
        }

        if (quantidade > quantidadeEmEstoque) {
            throw new IllegalArgumentException("Estoque insuficiente para retirar essa quantidade.");
        }

        this.quantidadeEmEstoque -= quantidade;
    }

    public boolean estaComEstoqueBaixo() {
        return quantidadeEmEstoque <= estoqueMinimo;
    }

    public boolean estaVencido() {
        return dataValidade != null && dataValidade.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", concentracao='" + concentracao + '\'' +
                ", quantidadeEmEstoque=" + quantidadeEmEstoque +
                ", estoqueMinimo=" + estoqueMinimo +
                ", dataValidade=" + dataValidade +
                ", estoqueBaixo=" + estaComEstoqueBaixo() +
                ", vencido=" + estaVencido() +
                '}';
    }
}