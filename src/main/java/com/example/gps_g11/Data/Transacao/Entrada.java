package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.CategoriaEntradas;

import java.io.Serializable;
import java.time.LocalDate;

public class Entrada extends Transacao implements Serializable {

    private CategoriaEntradas categoria;

    public Entrada(String descricao, LocalDate data, double montante, CategoriaEntradas categoria,boolean isDinheiro) {
        super(descricao, data, montante,isDinheiro);
        this.categoria = categoria;
    }

    public CategoriaEntradas getCategoria() {
        return categoria;
    }

}
