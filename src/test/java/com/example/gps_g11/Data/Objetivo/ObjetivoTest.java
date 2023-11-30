package com.example.gps_g11.Data.Objetivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class ObjetivoTest {

    @Test
    public void testAddToGoal() {
        Objetivo objetivo = new Objetivo("Férias", "Viagem dos sonhos", 5000.0);
        assertEquals(0.0, objetivo.getCurrentValue(), 0.01);

        assertEquals(0.0, objetivo.addToGoal(3000.0), 0.01);
        assertEquals(3000.0, objetivo.getCurrentValue(), 0.01);

        assertEquals(3000.0, objetivo.addToGoal(8000.0), 0.01);
        assertEquals(5000.0, objetivo.getCurrentValue(), 0.01);
    }

    @Test
    public void testIsFullfiled() {
        Objetivo objetivo = new Objetivo("Casa Própria", "Compra da casa própria", 200000.0);
        assertFalse(objetivo.isFullfiled());

        objetivo.addToGoal(200000.0);
        assertTrue(objetivo.isFullfiled());
    }

    @Test
    public void testGetMissingValue() {
        Objetivo objetivo = new Objetivo("Carro Novo", "Compra de carro zero km", 30000.0);
        assertEquals(30000.0, objetivo.getMissingValue(), 0.01);

        objetivo.addToGoal(15000.0);
        assertEquals(15000.0, objetivo.getMissingValue(), 0.01);
    }

    @Test
    public void testGetCurrentValue() {
        Objetivo objetivo = new Objetivo("Curso Online", "Investimento em educação", 1500.0);
        assertEquals(0.0, objetivo.getCurrentValue(), 0.01);

        objetivo.addToGoal(1000.0);
        assertEquals(1000.0, objetivo.getCurrentValue(), 0.01);
    }
}
