package com.example.gps_g11.Data.Objetivo;

import java.util.Date;

public class Objetivo {
    private String nome, descricao;
    private double valor;

    public Objetivo(String nome, String descricao, double valor){
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }
    public Objetivo(String nome, double valor){
        this.nome = nome;
        this.descricao = "";
        this.valor = valor;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Double getValor() {
        return valor;
    }
}
