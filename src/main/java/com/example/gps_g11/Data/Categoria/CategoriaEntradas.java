package com.example.gps_g11.Data.Categoria;

import java.io.Serializable;

public class CategoriaEntradas implements Serializable {
    private String nome;
    private String descricao;
    private double valor;

    public CategoriaEntradas(String nome, String descricao,double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
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
}
