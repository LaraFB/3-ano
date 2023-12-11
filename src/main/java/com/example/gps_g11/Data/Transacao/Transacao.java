package com.example.gps_g11.Data.Transacao;

import java.io.Serializable;
import java.time.LocalDate;

public class Transacao implements Serializable {
    private static int nextId = 0;
    private int id;
    protected String descricao;
    protected LocalDate data;
    protected double montante;
    protected double saldoAtual; //saldo com que ficou depois da trasnação

    protected boolean isDinheiro;

    public Transacao(String descricao, LocalDate data, double montante, boolean isDinheiro,double saldoAtual) {
        this.id = nextId++;
        this.descricao = descricao;
        this.data = data;
        this.montante = montante;
        this.isDinheiro = isDinheiro;
        this.saldoAtual = saldoAtual;
    }

    public int getId() {
        return id;
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

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }


}
