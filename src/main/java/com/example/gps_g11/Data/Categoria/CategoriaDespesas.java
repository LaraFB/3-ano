package com.example.gps_g11.Data.Categoria;

import java.io.Serializable;

public class CategoriaDespesas extends CategoriaEntradas implements Serializable{
    private boolean isAberto;
    public CategoriaDespesas(String nome, String descricao, double valor,boolean isAberto) {
        super(nome, descricao, valor);
        this.isAberto = isAberto;
    }

    public boolean isAberto() {
        return isAberto;
    }

    public void setAberto(boolean aberto) {
        isAberto = aberto;
    }
}
