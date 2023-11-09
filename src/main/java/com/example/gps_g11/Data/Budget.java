package com.example.gps_g11.Data;

public class Budget {
    private double budgetRestante;
    private double budgetGasto;
    private boolean excedeuBudget;
    private Bolsa bolsa;

    public Budget(double budgetRestante) {
        //Exemplo de valores
        this.budgetRestante = budgetRestante;
        this.budgetGasto = 49;
        this.excedeuBudget = false;
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


    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }
}
