package com.example.gps_g11.Data.Budget;

public class Bolsa {
    private String nome;
    private double valor;
    private String descricao;
    private double valorGasto;

    public Bolsa(String nome, double valor, String descricao) {
        this.nome = nome;
        this.valor = valor-10;
        this.descricao = descricao;
        valorGasto = 10;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }
}
