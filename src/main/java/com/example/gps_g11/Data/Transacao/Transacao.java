package com.example.gps_g11.Data.Transacao;

import java.io.Serializable;
import java.time.LocalDate;

public class Transacao implements Serializable {
    private static int nextId = 0;
    private int id;
    private String descricao;
    private LocalDate data;
    private double montante;

    private boolean isDinheiro;

    public Transacao(String descricao, LocalDate data, double montante, boolean isDinheiro) {
        this.id = nextId++;
        this.descricao = descricao;
        this.data = data;
        this.montante = montante;
        this.isDinheiro = isDinheiro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getMontante() {
        return montante;
    }

    public void setMontante(double montante) {
        this.montante = montante;
    }

    public boolean isDinheiro() {
        return isDinheiro;
    }

    public void setDinheiro(boolean dinheiro) {
        isDinheiro = dinheiro;
    }
}
