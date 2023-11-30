package com.example.gps_g11.Data.Categoria;

import java.io.Serializable;

public class Categoria implements Serializable {
    private String nome;
    private String descricao;
    private double valor;
    private boolean isAberto;
    private boolean pagarBolsa;
    public Categoria(double valor,String nome,boolean isAberto,boolean pagarBolsa){
        //validation:
        if(nome != null) this.nome = nome;
        else this.nome = "";
        this.valor = valor;
        this.descricao = "";
        this.isAberto = isAberto;
        this.pagarBolsa = pagarBolsa;
    }

    public Categoria(double valor,String nome, String descricao,boolean isAberto,boolean pagarBolsa){
        if(nome != null) this.nome = nome;
        else this.nome = "";
        this.valor = valor;
        if(descricao != null) this.descricao = descricao;
        else this.descricao = "";
        this.isAberto = isAberto;
        this.pagarBolsa = pagarBolsa;
    }

    public void setNome(String nome) {
        if(nome != null) this.nome = nome;
    }

    public void setDescricao(String descricao) {
        if(descricao != null) this.descricao = descricao;
    }
    public String getNome() { return nome; }

    public String getDescricao() { return descricao; }
    public boolean isAberto() {
        return isAberto;
    }

    public void setAberto(boolean aberto) {
        isAberto = aberto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isPagarBolsa() {
        return pagarBolsa;
    }

    public void setPagarBolsa(boolean pagarBolsa) {
        this.pagarBolsa = pagarBolsa;
    }

}
