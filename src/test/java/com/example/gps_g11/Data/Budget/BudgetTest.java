package com.example.gps_g11.Data.Budget;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    @Test
    void getBudgetRestante() {
        Budget budget = new Budget(100.0);
        assertEquals(100.0,budget.getBudgetRestante());
    }

    @Test
    void getBudgetGasto() {
        Budget budget = new Budget(100.0);
        budget.setBudgetGasto(50.0);
        assertEquals(50.0,budget.getBudgetGasto());
    }

    @Test
    void getBudgetGuardado() {
        Budget budget = new Budget(100.0);
        budget.setBudgetGuardado(50.0);
        assertEquals(50.0,budget.getBudgetGuardado());
    }
    @Test
    void isExcedeuBudget() {
        Budget budget = new Budget(100.0);
        budget.setExcedeuBudget(true);
        assertTrue(budget.isExcedeuBudget());
    }

    @Test
    public void getBolsa() {
        Budget budget = new Budget(500.0);
        Bolsa bolsaEsperada = new Bolsa("ISEC", 120, "Bolsa de estudo");
        budget.setBolsa(bolsaEsperada);
        Bolsa bolsaObtida = budget.getBolsa();
        assertEquals(bolsaEsperada, bolsaObtida);
    }

    @Test
    void adicionarAoBudgetGasto() {
        Budget budget = new Budget(500.0);
        budget.adicionarAoBudgetGasto(100.0);
        assertEquals(400.0, budget.getBudgetRestante(), 0.001);
        assertEquals(100.0, budget.getBudgetGasto(), 0.001);
    }

    @Test
    void adicionarAoBudgetGuardado() {
        Budget budget = new Budget(500.0);
        budget.adicionarAoBudgetGuardado(50.0);
        assertEquals(450.0, budget.getBudgetRestante(), 0.001);
        assertEquals(50.0, budget.getBudgetGuardado(), 0.001);
    }

    @Test
    void retirarDoBudgetGasto() {
        Budget budget = new Budget(500.0);
        budget.adicionarAoBudgetGasto(100.0);
        budget.retirarDoBudgetGasto(30.0);
        assertEquals(430.0, budget.getBudgetRestante(), 0.001);
        assertEquals(70.0, budget.getBudgetGasto(), 0.001);
    }

    @Test
    void retirarDoBudgetGuardado() {
        Budget budget = new Budget(500.0);
        budget.adicionarAoBudgetGuardado(50.0);
        budget.retirarDoBudgetGuardado(20.0);
        assertEquals(470, budget.getBudgetRestante(), 0.001);
        assertEquals(30.0, budget.getBudgetGuardado(), 0.001);
    }

    @Test
    void getEnvelopes() {
    }
}