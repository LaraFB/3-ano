package com.example.gps_g11.Data.Categoria;

import java.io.Serializable;

public class Categoria implements Serializable {
    private String nome;
    private String descricao;

    private boolean isAberto;

    public Categoria(String nome,boolean isAberto){
        //validation:
        if(nome != null) this.nome = nome;
        else this.nome = "";

        this.descricao = "";
        this.isAberto = isAberto;
    }

    public Categoria(String nome, String descricao,boolean isAberto){
        if(nome != null) this.nome = nome;
        else this.nome = "";

        if(descricao != null) this.descricao = descricao;
        else this.descricao = "";
        this.isAberto = isAberto;
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
}
