package com.example.gps_g11.Data.Budget;

import java.io.Serializable;

public class Bolsa  implements Serializable {
    private String nome;
    private double valorReal;
    private double valorDisponivel;
    private double totalDespesas; //Total de despesas gastas
    private String descricao;
    private double valorGasto;

    public Bolsa(String nome, double valor, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorReal = this.valorDisponivel = valor;
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

    public double getValorReal() {
        return valorReal;
    }

    public void setValorReal(double valorReal) {
        this.valorReal = valorReal;
    }

    public double getValorDisponivel() {
        return valorDisponivel;
    }

    public void setValorDisponivel(double valorDisponivel) {
        this.valorDisponivel = valorDisponivel;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }
}
