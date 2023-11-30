package com.example.gps_g11.Data.Budget;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTest {

    @Test
    public void testConstrutor() {
        Budget budget = new Budget(1000.0);
        assertEquals(1000.0, budget.getSaldoReal(), 0.01);
        assertEquals(1000.0, budget.getSaldoDisponivel(), 0.01);
        assertEquals(0.0, budget.getTotalDespesas(), 0.01);
        assertFalse(budget.isExcedeuSaldo());
        assertNotNull(budget.getBolsa());
        assertEquals("Bolsa", budget.getBolsa().getNome());
        assertEquals(0.0, budget.getBolsa().getValorReal(), 0.01);
        assertEquals("Bolsa de estudo", budget.getBolsa().getDescricao());
    }

    @Test
    public void testSettersAndGetters() {
        Budget budget = new Budget(1000.0);

        budget.setSaldoReal(1500.0);
        assertEquals(1500.0, budget.getSaldoReal(), 0.01);

        budget.setSaldoDisponivel(1200.0);
        assertEquals(1200.0, budget.getSaldoDisponivel(), 0.01);

        budget.setTotalDespesas(500.0);
        assertEquals(500.0, budget.getTotalDespesas(), 0.01);

        budget.setExcedeuBudget(true);
        assertTrue(budget.isExcedeuSaldo());

        budget.setDiaReset(15);
        assertEquals(15, budget.getDiaReset());

        budget.setNvezes(3);
        assertEquals(3, budget.getNvezes());

        budget.setCald("Alguma Calendário");
        assertEquals("Alguma Calendário", budget.getCald());

        LocalDate today = LocalDate.now();
        budget.setHoje(today);
        assertEquals(today, budget.getHoje());

        Bolsa novaBolsa = new Bolsa("Nova Bolsa", 500.0, "Nova Descrição");
        budget.setBolsa(novaBolsa);
        assertEquals(novaBolsa, budget.getBolsa());
    }
    @Test
    public void testLimpaBudget() {
        Budget budget = new Budget(1000.0);

        budget.setTotalDespesas(500.0);
        assertEquals(500.0, budget.getTotalDespesas(), 0.01);

        budget.LimpaBudget();
        assertEquals(0.0, budget.getTotalDespesas(), 0.01);
    }
}
