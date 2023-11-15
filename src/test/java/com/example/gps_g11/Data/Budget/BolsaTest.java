package com.example.gps_g11.Data.Budget;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BolsaTest {

    @Test
    public void testGetNome() {
        Bolsa bolsa = new Bolsa("ISEC", 1000.0, "Bolsa para estudantes");
        assertEquals("ISEC", bolsa.getNome());
    }

    @Test
    public void testSetNome() {
        Bolsa bolsa = new Bolsa("ISEC", 1000.0, "Bolsa para estudantes");
        bolsa.setNome("IPC");
        assertEquals("IPC", bolsa.getNome());
    }

    @Test
    public void testGetValor() {
        Bolsa bolsa = new Bolsa("ISEC", 1000.0, "Bolsa para estudantes");
        assertEquals(990.0, bolsa.getValor(), 0.001);
    }

    @Test
    public void testSetValor() {
        Bolsa bolsa = new Bolsa("ISEC", 1000.0, "Bolsa para estudantes");
        bolsa.setValor(1500.0);
        assertEquals(1500.0, bolsa.getValor(), 0.001);
    }

    @Test
    public void testGetDescricao() {
        Bolsa bolsa = new Bolsa("ISEC", 1000.0, "Bolsa para estudantes");
        assertEquals("Bolsa para estudantes", bolsa.getDescricao());
    }

    @Test
    public void testSetDescricao() {
        Bolsa bolsa = new Bolsa("ISEC", 1000.0, "Bolsa para estudantes");
        bolsa.setDescricao("Nova descrição");
        assertEquals("Nova descrição", bolsa.getDescricao());
    }

    @Test
    public void testToString() {
        Bolsa bolsa = new Bolsa("ISEC", 1000.0, "Bolsa para estudantes");
        assertEquals("Bolsa{nome='ISEC', valor=990.0, descricao='Bolsa para estudantes'}", bolsa.toString());
    }

    @Test
    public void testGetValorGasto() {
        Bolsa bolsa = new Bolsa("ISEC", 1000.0, "Bolsa para estudantes");
        assertEquals(10.0, bolsa.getValorGasto(), 0.001);
    }

    @Test
    public void testSetValorGasto() {
        Bolsa bolsa = new Bolsa("ISEC", 1000.0, "Bolsa para estudantes");
        bolsa.setValorGasto(20.0);
        assertEquals(20.0, bolsa.getValorGasto(), 0.001);
    }
}