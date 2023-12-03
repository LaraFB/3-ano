package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.CategoriaDespesas;

import java.io.Serializable;

import java.time.LocalDate;

public class Despesa extends Transacao implements Serializable {
    private CategoriaDespesas categoria;

    public Despesa(String descricao, LocalDate data, double montante, CategoriaDespesas categoria,boolean isDinheiro) {
        super(descricao, data, montante,isDinheiro);
        this.categoria = categoria;
    }

    public CategoriaDespesas getCategoria() {
        return categoria;
    }
}
