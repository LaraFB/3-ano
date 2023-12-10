package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.CategoriaDespesas;

import java.io.Serializable;

import java.time.LocalDate;

public class Despesa extends Transacao implements Serializable {
    private CategoriaDespesas categoria;

    public Despesa(String descricao, LocalDate data, double montante, CategoriaDespesas categoria,boolean isDinheiro,double saldoAtual) {
        super(descricao, data, montante,isDinheiro,saldoAtual);
        this.categoria = categoria;
    }

    public CategoriaDespesas getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "categoria=" + categoria +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", montante=" + montante +
                ", saldoAtual=" + saldoAtual +
                ", isDinheiro=" + isDinheiro +
                '}';
    }
}
