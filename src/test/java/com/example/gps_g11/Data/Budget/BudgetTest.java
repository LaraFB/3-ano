package com.example.gps_g11.Data.Budget;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTest {
    @Test
    public void testGetSaldoReal() {
        Budget budget = new Budget(100.0);
        double saldoReal = budget.getSaldoReal();
        assertEquals(100.0, saldoReal, 0.01); // Usando delta para lidar com imprecisões de ponto flutuante
    }

    @Test
    public void testSetSaldoReal() {
        Budget budget = new Budget(100.0);
        budget.setSaldoReal(150.0);
        assertEquals(150.0, budget.getSaldoReal(), 0.01);
    }

    @Test
    public void testIsExcedeuSaldo() {
        Budget budget = new Budget(100.0);
        boolean excedeuSaldo = budget.isExcedeuSaldo();
        assertFalse(excedeuSaldo);
    }

    @Test
    public void testSetExcedeuSaldo() {
        Budget budget = new Budget(100.0);
        budget.setExcedeuSaldo(true);
        assertTrue(budget.isExcedeuSaldo());
    }
}