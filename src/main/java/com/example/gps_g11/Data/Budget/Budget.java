package com.example.gps_g11.Data.Budget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Budget implements Serializable {
    private double budgetRestante;
    private double budgetGasto;
    private double budgetGuardado;
    private boolean excedeuBudget;
    /*private Bolsa bolsa;
    private List<Envelope> envelopes;*/

    public Budget(double budgetRestante) {
        this.budgetRestante = budgetRestante;
        this.excedeuBudget = false;
        //this.envelopes = new ArrayList<>();
    }

    public double getBudgetRestante() {
        return budgetRestante;
    }

    public void setBudgetRestante(double budgetRestante) {
        this.budgetRestante = budgetRestante;
    }

    public double getBudgetGasto() {
        return budgetGasto;
    }

    public void setBudgetGasto(double budgetGasto) {
        this.budgetGasto = budgetGasto;
    }

    public boolean isExcedeuBudget() {
        return excedeuBudget;
    }

    public void setExcedeuBudget(boolean excedeuBudget) {
        this.excedeuBudget = excedeuBudget;
    }


   /* public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }*/

    public void adicionarAoBudgetGasto(double valor) {
        this.budgetGasto += valor;
        this.budgetRestante -= valor;
    }

    public void adicionarAoBudgetGuardado(double valor) {
        this.budgetGuardado += valor;
        this.budgetRestante -= valor;
    }

    public void retirarDoBudgetGasto(double valor) {
        this.budgetGasto -= valor;
        this.budgetRestante += valor;
    }

    public void retirarDoBudgetGuardado(double valor) {
        this.budgetGuardado -= valor;
        this.budgetRestante += valor;
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

    public double getBudgetGuardado() {
        return budgetGuardado;
    }

    public void setBudgetGuardado(double budgetGuardado) {
        this.budgetGuardado = budgetGuardado;
    }
}
