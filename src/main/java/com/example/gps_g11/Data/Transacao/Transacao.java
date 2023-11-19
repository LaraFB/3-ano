package com.example.gps_g11.Data.Transacao;

import com.example.gps_g11.Data.Categoria.Categoria;

import java.io.Serializable;

import java.time.LocalDate;

public class Transacao implements Serializable {
    private static int nextId = 0;
    private int id;
    private String tipo; // 'Despesa' ou 'Entrada'
    private String descricao;
    private LocalDate data;
    private float montante;
    private Categoria categoria;
    private boolean recorrente;

    public Transacao(String tipo, String descricao,Categoria categoria, LocalDate data, float montante) {
        this.id = nextId++;
        this.tipo = tipo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.data = data;
        this.montante = montante;

    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public float getMontante() {
        return montante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setMontante(float montante) {
        this.montante = montante;
    }
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isRecorrente() {
        return recorrente;
    }

    public void setRecorrente(boolean recorrente) {
        this.recorrente = recorrente;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", montante=" + montante +
                ", categoria='" + categoria + '\'' +
                ", recorrente=" + recorrente +
                '}';
    }
}
