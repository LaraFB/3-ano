package com.example.gps_g11.Data.Categoria;

import java.io.Serializable;

public class CategoriaDespesas extends CategoriaEntradas implements Serializable{
    private boolean isAberto;
    private boolean isRecorrente;
    public CategoriaDespesas(String nome, String descricao, double valor,boolean isAberto, boolean isRecorrente) {
        super(nome, descricao, valor);
        this.isAberto = isAberto;
        this.isRecorrente = isRecorrente;
    }

    public boolean isAberto() {
        return isAberto;
    }
    public void setAberto(boolean aberto) {
        isAberto = aberto;
    }
    public boolean isRecorrente(){return isRecorrente;}
    public void switchRecorrente(){isRecorrente = !isRecorrente;}
}
