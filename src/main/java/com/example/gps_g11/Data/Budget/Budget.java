package com.example.gps_g11.Data.Budget;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Budget implements Serializable {
    private double saldoReal; //Saldo que existe mesmo
    private double saldoDisponivel; //Saldo que existe menos o saldo que vai ser gasto posteriormente
    private double totalDespesas; //Total de despesas gastas
    private boolean excedeuSaldo;
    private int DiaReset;
    private int nvezes;
    private String cald;
    private LocalDate hoje;
    private Bolsa bolsa;

    public Budget(double saldo) {
        this.saldoReal = this.saldoDisponivel = saldo;
        totalDespesas = 0;
        excedeuSaldo = false;
        bolsa = new Bolsa("Bolsa",0,"Bolsa de estudo");
    }

    public void LimpaBudget (){totalDespesas = 0;}

    public double getSaldoReal() {
        return saldoReal;
    }

    public void setSaldoReal(double saldoReal) {
        this.saldoReal = saldoReal;
    }

    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(double saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public boolean isExcedeuSaldo() {
        return excedeuSaldo;
    }

    public void setExcedeuBudget(boolean excedeuSaldo) {
        this.excedeuSaldo = excedeuSaldo;
    }


    public int getDiaReset() {
        return DiaReset;
    }

    public void setDiaReset(int diaReset) {
        DiaReset = diaReset;
    }

    public int getNvezes() {
        return nvezes;
    }

    public void setNvezes(int nvezes) {
        this.nvezes = nvezes;
    }

    public String getCald() {
        return cald;
    }

    public void setCald(String cald) {
        this.cald = cald;
    }

    public LocalDate getHoje() {
        return hoje;
    }

    public void setHoje(LocalDate hoje) {
        this.hoje = hoje;
    }

    /* public List<Envelope> getEnvelopes() {
        return envelopes;
    }*/

    /*public void criarEnvelope(String finalidade, double valor) {
        if (valor <= this.budgetRestante) {
            Envelope envelope = new Envelope(finalidade, valor);
            this.envelopes.add(envelope);
            this.budgetGuardado += valor;
            this.budgetRestante -= valor;

            System.out.println("Envelope criado com sucesso!");
        } else {
            System.out.println("Valor excede o orçamento restante. Envelope não criado.");
        }
    }*/
   /* public void setEnvelopes(List<Envelope> envelopes) {
        this.envelopes = envelopes;
    }*/
    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }

}
