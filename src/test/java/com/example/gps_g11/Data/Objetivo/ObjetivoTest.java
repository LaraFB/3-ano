package com.example.gps_g11.Data.Objetivo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ObjetivoTest {

    @Test
    public void testGetSetNome() {
        Objetivo objetivo = new Objetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals("Comprar um carro", objetivo.getNome());

        objetivo.setNome("Viajar");
        assertEquals("Viajar", objetivo.getNome());
    }

    @Test
    public void testGetSetDescricao() {
        Objetivo objetivo = new Objetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals("Economizar para comprar um carro", objetivo.getDescricao());

        objetivo.setDescricao("Economizar para uma viagem");
        assertEquals("Economizar para uma viagem", objetivo.getDescricao());
    }

    @Test
    public void testGetSetValor() {
        Objetivo objetivo = new Objetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals(20000.0, objetivo.getValor(), 0.01);

        objetivo.setValor(25000.0);
        assertEquals(25000.0, objetivo.getValor(), 0.01);
    }

    @Test
    public void testGetSetPrioridade() {
        Objetivo objetivo = new Objetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals(1, objetivo.getPrioridade());

        objetivo.setPrioridade(2);
        assertEquals(2, objetivo.getPrioridade());
    }

    @Test
    public void testGetSetDataLimite() {
        Objetivo objetivo = new Objetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertNull(objetivo.getDataLimite());

        LocalDate dataLimite = LocalDate.of(2023, 12, 31);
        objetivo.setDataLimite(dataLimite);
        assertEquals(dataLimite, objetivo.getDataLimite());
    }

    @Test
    public void testAddToGoal() {
        Objetivo objetivo = new Objetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals(0.0, objetivo.addToGoal(5000.0), 0.01);
        assertEquals(5000.0, objetivo.getCurrentValue(), 0.01);

        assertEquals(0.0, objetivo.addToGoal(15000.0), 0.01);
        assertEquals(20000.0, objetivo.getCurrentValue(), 0.01);

        assertEquals(-15000.0, objetivo.addToGoal(5000.0), 0.01);
        assertEquals(20000.0, objetivo.getCurrentValue(), 0.01);
    }

    @Test
    public void testIsFullfilled() {
        Objetivo objetivo = new Objetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertFalse(objetivo.isFullfiled());

        objetivo.addToGoal(20000.0);
        assertTrue(objetivo.isFullfiled());
    }

    @Test
    public void testGetMissingValue() {
        Objetivo objetivo = new Objetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals(20000.0, objetivo.getMissingValue(), 0.01);

        objetivo.addToGoal(5000.0);
        assertEquals(15000.0, objetivo.getMissingValue(), 0.01);

        objetivo.addToGoal(10000.0);
        assertEquals(5000.0, objetivo.getMissingValue(), 0.01);

        objetivo.addToGoal(5000.0);
        assertEquals(0.0, objetivo.getMissingValue(), 0.01);
    }

    @Test
    public void testGetCurrentValue() {
        Objetivo objetivo = new Objetivo("Comprar um carro", "Economizar para comprar um carro", 20000.0, 1);
        assertEquals(0.0, objetivo.getCurrentValue(), 0.01);

        objetivo.addToGoal(5000.0);
        assertEquals(5000.0, objetivo.getCurrentValue(), 0.01);

        objetivo.addToGoal(15000.0);
        assertEquals(20000.0, objetivo.getCurrentValue(), 0.01);
    }
}
