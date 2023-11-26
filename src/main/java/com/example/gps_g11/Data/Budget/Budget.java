package com.example.gps_g11.Data.Budget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Budget implements Serializable {
    private double saldoReal; //Saldo que existe mesmo
    private double saldoDisponivel; //Saldo que existe menos o saldo que vai ser gasto posteriormente
    private double totalDespesas; //Total de despesas gastas
    private double saldoGuardado;
    private boolean excedeuSaldo;
    private Bolsa bolsa;

    public Budget(double saldo) {
        this.saldoReal = this.saldoDisponivel = saldo;
        saldoGuardado = 0;
        totalDespesas = 0;
        excedeuSaldo = false;
    }

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

    public double getSaldoGuardado() {
        return saldoGuardado;
    }

    public void setSaldoGuardado(double saldoGuardado) {
        this.saldoGuardado = saldoGuardado;
    }
    public boolean isExcedeuSaldo() {
        return excedeuSaldo;
    }

    public void setExcedeuBudget(boolean excedeuSaldo) {
        this.excedeuSaldo = excedeuSaldo;
    }
    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }

    public void addSaldo(double valor){
        this.saldoReal += valor;
        this.saldoDisponivel += valor;
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
}
