package com.example.gps_g11.Data.Budget;

public class Saldo {
    private Budget budgetDinheiro;
    private Budget budgetContaBancaria;
    private double saldoPorDistribuir; //Saldo que falta distribuir pelos envelopes
    private double saldoNosEnvelopes; //Saldo nos envelopes
    private double totalDespesas; //Total de despesas gastas

    public Saldo() {
        this.budgetDinheiro = new Budget(0);
        this.budgetContaBancaria = new Budget(0);
        this.saldoPorDistribuir = 0;
        this.saldoNosEnvelopes = 0;
        this.totalDespesas = 0;
    }

    public Budget getBudgetDinheiro() {
        return budgetDinheiro;
    }

    public Budget getBudgetContaBancaria() {
        return budgetContaBancaria;
    }


    public double getSaldoPorDistribuir() {
        return saldoPorDistribuir;
    }

    public void setSaldoPorDistribuir(double saldoPorDistribuir) {
        this.saldoPorDistribuir = saldoPorDistribuir;
    }

    public double getSaldoNosEnvelopes() {
        return saldoNosEnvelopes;
    }

    public void setSaldoNosEnvelopes(double saldoNosEnvelopes) {
        this.saldoNosEnvelopes = saldoNosEnvelopes;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }
}
