package com.example.gps_g11.Data.Categoria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoriaDespesasTest {

    @Test
    public void testIsAberto() {
        CategoriaDespesas categoria = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true);
        assertTrue(categoria.isAberto());
    }

    @Test
    public void testSetAberto() {
        CategoriaDespesas categoria = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true);
        categoria.setAberto(false);
        assertFalse(categoria.isAberto());
    }

    @Test
    public void testGetNome() {
        CategoriaDespesas categoria = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true);
        assertEquals("Transporte", categoria.getNome());
    }

    @Test
    public void testSetNome() {
        CategoriaDespesas categoria = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true);
        categoria.setNome("Alimentação");
        assertEquals("Alimentação", categoria.getNome());
    }

    @Test
    public void testGetDescricao() {
        CategoriaDespesas categoria = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true);
        assertEquals("Despesas com transporte", categoria.getDescricao());
    }

    @Test
    public void testSetDescricao() {
        CategoriaDespesas categoria = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true);
        categoria.setDescricao("Despesas com habitação");
        assertEquals("Despesas com habitação", categoria.getDescricao());
    }

    @Test
    public void testGetValor() {
        CategoriaDespesas categoria = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true);
        assertEquals(50.0, categoria.getValor(), 0.01);
    }

    @Test
    public void testSetValor() {
        CategoriaDespesas categoria = new CategoriaDespesas("Transporte", "Despesas com transporte", 50.0, true);
        categoria.setValor(75.0);
        assertEquals(75.0, categoria.getValor(), 0.01);
    }
}
