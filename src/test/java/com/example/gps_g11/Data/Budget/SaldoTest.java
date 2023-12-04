package com.example.gps_g11.Data.Budget;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaldoTest {

    @Test
    public void testGetBudgetDinheiro() {
        Saldo saldo = new Saldo();
        Budget budgetDinheiro = saldo.getBudgetDinheiro();
        assertNotNull(budgetDinheiro);
        assertEquals(0.0, budgetDinheiro.getSaldoReal(), 0.01);
    }

    @Test
    public void testGetBudgetContaBancaria() {
        Saldo saldo = new Saldo();
        Budget budgetContaBancaria = saldo.getBudgetContaBancaria();
        assertNotNull(budgetContaBancaria);
        assertEquals(0.0, budgetContaBancaria.getSaldoReal(), 0.01);
    }

    @Test
    public void testGetSaldoPorDistribuir() {
        Saldo saldo = new Saldo();
        double saldoPorDistribuir = saldo.getSaldoPorDistribuir();
        assertEquals(0.0, saldoPorDistribuir, 0.01);
    }

    @Test
    public void testSetSaldoPorDistribuir() {
        Saldo saldo = new Saldo();
        saldo.setSaldoPorDistribuir(50.0);
        assertEquals(50.0, saldo.getSaldoPorDistribuir(), 0.01);
    }

    @Test
    public void testGetSaldoNosEnvelopes() {
        Saldo saldo = new Saldo();
        double saldoNosEnvelopes = saldo.getSaldoNosEnvelopes();
        assertEquals(0.0, saldoNosEnvelopes, 0.01);
    }

    @Test
    public void testSetSaldoNosEnvelopes() {
        Saldo saldo = new Saldo();
        saldo.setSaldoNosEnvelopes(30.0);
        assertEquals(30.0, saldo.getSaldoNosEnvelopes(), 0.01);
    }

    @Test
    public void testGetTotalDespesas() {
        Saldo saldo = new Saldo();
        double totalDespesas = saldo.getTotalDespesas();
        assertEquals(0.0, totalDespesas, 0.01);
    }

    @Test
    public void testSetTotalDespesas() {
        Saldo saldo = new Saldo();
        saldo.setTotalDespesas(100.0);
        assertEquals(100.0, saldo.getTotalDespesas(), 0.01);
    }
}
