package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.CategoriaEntradas;

import java.io.Serializable;
import java.time.LocalDate;

public class Entrada extends Transacao implements Serializable {

    private CategoriaEntradas categoria;

    public Entrada(String descricao, LocalDate data, double montante, CategoriaEntradas categoria,boolean isDinheiro,double saldoAtual) {
        super(descricao, data, montante,isDinheiro,saldoAtual);
        this.categoria = categoria;
    }

    public CategoriaEntradas getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Entrada{" +
                "categoria=" + categoria +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", montante=" + montante +
                ", saldoAtual=" + saldoAtual +
                ", isDinheiro=" + isDinheiro +
                '}';
    }
}
